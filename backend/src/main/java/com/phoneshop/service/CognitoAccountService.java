
package com.phoneshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phoneshop.entity.KhachHang;
import com.phoneshop.entity.VaiTro;
import com.phoneshop.repository.KhachHangRepository;
import com.phoneshop.repository.NhanVienRepository;
import com.phoneshop.repository.VaiTroRepository;

/**
 * Service quản lý liên kết tài khoản Amazon Cognito với dữ liệu khách hàng và
 * nhân viên trong MySQL.
 */
@Service
@Transactional(readOnly = true)
public class CognitoAccountService {

	// Repository quản lý khách hàng.
	private final KhachHangRepository khachHangRepository;

	// Repository quản lý nhân viên.
	private final NhanVienRepository nhanVienRepository;

	// Repository quản lý vai trò trong MySQL.
	private final VaiTroRepository vaiTroRepository;

	/**
	 * Constructor Dependency Injection.
	 */
	public CognitoAccountService(KhachHangRepository khachHangRepository, NhanVienRepository nhanVienRepository,
			VaiTroRepository vaiTroRepository) {

		this.khachHangRepository = khachHangRepository;
		this.nhanVienRepository = nhanVienRepository;
		this.vaiTroRepository = vaiTroRepository;
	}

	/**
	 * Tìm tài khoản MySQL bằng CognitoSub.
	 *
	 * CognitoSub phải lấy từ JWT đã được xác thực.
	 */
	public AccountLinkResult findAccount(String cognitoSub) {

		// Kiểm tra định danh Cognito.
		if (cognitoSub == null || cognitoSub.isBlank()) {
			throw new IllegalArgumentException("CognitoSub không hợp lệ.");
		}

		// Tìm khách hàng đang hoạt động.
		var khachHang = khachHangRepository.findByCognitoSubAndTrangThai(cognitoSub, true);

		// Tìm nhân viên đang hoạt động.
		var nhanVien = nhanVienRepository.findByCognitoSubAndTrangThai(cognitoSub, true);

		// Không cho phép liên kết trùng hai loại tài khoản.
		if (khachHang.isPresent() && nhanVien.isPresent()) {
			throw new IllegalStateException("CognitoSub liên kết với nhiều tài khoản.");
		}

		// Trả về thông tin khách hàng.
		if (khachHang.isPresent()) {
			return new AccountLinkResult(true, "CUSTOMER", khachHang.get().getMaKH());
		}

		// Trả về thông tin nhân viên.
		if (nhanVien.isPresent()) {
			return new AccountLinkResult(true, "EMPLOYEE", nhanVien.get().getMaNhanVien());
		}

		// Chưa có tài khoản được liên kết.
		return new AccountLinkResult(false, null, null);
	}

	/**
	 * Tạo khách hàng mới từ tài khoản Cognito đã xác minh.
	 *
	 * Chỉ được gọi sau khi Controller kiểm tra: - Access Token hợp lệ và thuộc đúng
	 * App Client. - Người dùng có nhóm CUSTOMER. - Người dùng không thuộc nhóm
	 * ADMIN hoặc STAFF. - Email đã được xác minh qua Cognito.
	 *
	 * Không tự liên kết tài khoản cũ chỉ bằng email.
	 */
	@Transactional
	public AccountLinkResult createCustomerIfMissing(String cognitoSub, String email) {

		// Kiểm tra CognitoSub.
		if (cognitoSub == null || cognitoSub.isBlank()) {
			throw new IllegalArgumentException("CognitoSub không hợp lệ.");
		}

		// Kiểm tra email.
		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException("Email không hợp lệ.");
		}

		String normalizedEmail = email.trim();

		// Kiểm tra độ dài theo cấu trúc MySQL.
		if (normalizedEmail.length() > 100) {
			throw new IllegalArgumentException("Email vượt quá độ dài cho phép.");
		}

		// Không cho phép CognitoSub đã thuộc nhân viên
		// được sử dụng để tạo khách hàng.
		if (nhanVienRepository.findByCognitoSub(cognitoSub).isPresent()) {

			throw new IllegalStateException("CognitoSub đã thuộc tài khoản nhân viên.");
		}

		// Tìm khách hàng đã liên kết với Cognito.
		var existingCustomer = khachHangRepository.findByCognitoSub(cognitoSub);

		if (existingCustomer.isPresent()) {

			KhachHang customer = existingCustomer.get();

			// Từ chối tài khoản đã bị vô hiệu hóa.
			if (!Boolean.TRUE.equals(customer.getTrangThai())) {
				throw new IllegalStateException("Tài khoản khách hàng đã bị vô hiệu hóa.");
			}

			// Không tạo tài khoản trùng lặp.
			return new AccountLinkResult(true, "CUSTOMER", customer.getMaKH());
		}

		// Không tự động chiếm liên kết của tài khoản
		// khách hàng hoặc nhân viên cũ qua email.
		if (khachHangRepository.existsByEmail(normalizedEmail) || nhanVienRepository.existsByEmail(normalizedEmail)) {

			throw new IllegalStateException("Email đã tồn tại. Cần xác minh để liên kết tài khoản cũ.");
		}

		// Lấy vai trò CUSTOMER từ bảng VaiTro.
		VaiTro vaiTro = vaiTroRepository.findByTenVaiTro("CUSTOMER");

		if (vaiTro == null) {
			throw new IllegalStateException("Không tìm thấy vai trò CUSTOMER.");
		}

		// Tạo đối tượng khách hàng mới.
		KhachHang khachHang = new KhachHang();

		// Gán định danh Cognito.
		khachHang.setCognitoSub(cognitoSub);

		// Lưu email đã được xác minh.
		khachHang.setEmail(normalizedEmail);

		// Tên mặc định, có thể cập nhật sau.
		khachHang.setHoTen("Khách hàng");

		// Không lưu mật khẩu Cognito trong MySQL.
		khachHang.setMatKhau(null);

		// Số điện thoại chưa bắt buộc.
		khachHang.setSoDienThoai(null);

		// Chỉ gán vai trò CUSTOMER.
		khachHang.setVaiTro(vaiTro);

		// Kích hoạt tài khoản mới.
		khachHang.setTrangThai(true);

		// Lưu khách hàng vào MySQL.
		// UNIQUE trên Email và CognitoSub giúp
		// ngăn bản ghi trùng khi có yêu cầu đồng thời.
		KhachHang saved = khachHangRepository.saveAndFlush(khachHang);

		// Trả về mã khách hàng vừa tạo.
		return new AccountLinkResult(true, "CUSTOMER", saved.getMaKH());
	}

	/**
	 * DTO trả về kết quả liên kết tài khoản.
	 *
	 * linked: Đã có tài khoản MySQL hay chưa. accountType: CUSTOMER hoặc EMPLOYEE.
	 * accountId: Mã khách hàng hoặc nhân viên.
	 */
	public record AccountLinkResult(boolean linked, String accountType, Integer accountId) {
	}
}
