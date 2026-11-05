package com.phoneshop.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phoneshop.dto.DangKyKhachHangRequestDTO;
import com.phoneshop.dto.DangKyKhachHangResponseDTO;
import com.phoneshop.entity.KhachHang;
import com.phoneshop.entity.VaiTro;
import com.phoneshop.repository.KhachHangRepository;
import com.phoneshop.repository.NhanVienRepository;
import com.phoneshop.repository.VaiTroRepository;

/**
 * Service xử lý nghiệp vụ xác thực tài khoản. Bao gồm đăng ký, đăng nhập và
 * phân quyền người dùng.
 */
@Service
public class AuthService {

	// Repository truy vấn thông tin khách hàng.
	private final KhachHangRepository khachHangRepository;

	// Repository truy vấn thông tin nhân viên.
	private final NhanVienRepository nhanVienRepository;

	// Repository truy vấn các vai trò trong hệ thống.
	private final VaiTroRepository vaiTroRepository;

	// Thành phần mã hóa mật khẩu bằng BCrypt.
	private final PasswordEncoder passwordEncoder;

	/**
	 * Constructor Injection: Spring tự động cung cấp các Repository.
	 */
	public AuthService(KhachHangRepository khachHangRepository, NhanVienRepository nhanVienRepository,
			VaiTroRepository vaiTroRepository, PasswordEncoder passwordEncoder) {

		this.khachHangRepository = khachHangRepository;
		this.nhanVienRepository = nhanVienRepository;
		this.vaiTroRepository = vaiTroRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Kiểm tra email và số điện thoại đã được sử dụng chưa.
	 *
	 * @param request dữ liệu khách hàng gửi lên khi đăng ký
	 */
	private void kiemTraThongTinDangKy(DangKyKhachHangRequestDTO request) {

		// Kiểm tra email đã tồn tại trong bảng KhachHang.
		if (khachHangRepository.existsByEmail(request.getEmail())) {
			throw new IllegalArgumentException("Email đã được sử dụng");
		}

		// Kiểm tra email đã tồn tại trong bảng NhanVien.
		if (nhanVienRepository.existsByEmail(request.getEmail())) {
			throw new IllegalArgumentException("Email đã được sử dụng");
		}

		// Kiểm tra số điện thoại đã tồn tại trong bảng KhachHang.
		if (khachHangRepository.existsBySoDienThoai(request.getSoDienThoai())) {
			throw new IllegalArgumentException("Số điện thoại đã được sử dụng");
		}
	}

	/**
	 * Lấy vai trò CUSTOMER từ database.
	 *
	 * @return đối tượng VaiTro tương ứng với CUSTOMER
	 */
	private VaiTro layVaiTroKhachHang() {

		// Tìm vai trò CUSTOMER theo tên.
		VaiTro vaiTro = vaiTroRepository.findByTenVaiTro("CUSTOMER");

		// Kiểm tra vai trò có tồn tại trong database không.
		if (vaiTro == null) {
			throw new IllegalStateException("Không tìm thấy vai trò CUSTOMER trong hệ thống");
		}

		return vaiTro;
	}

	/**
	 * Tạo đối tượng KhachHang từ dữ liệu đăng ký.
	 *
	 * @param request dữ liệu khách hàng gửi lên
	 * @param vaiTro  vai trò CUSTOMER lấy từ database
	 * @return đối tượng KhachHang đã thiết lập thông tin
	 */
	private KhachHang taoKhachHang(DangKyKhachHangRequestDTO request, VaiTro vaiTro) {

		// Khởi tạo đối tượng khách hàng mới.
		KhachHang khachHang = new KhachHang();

		// Thiết lập thông tin cá nhân.
		khachHang.setHoTen(request.getHoTen());
		khachHang.setEmail(request.getEmail());
		khachHang.setSoDienThoai(request.getSoDienThoai());
		khachHang.setNgaySinh(request.getNgaySinh());
		khachHang.setGioiTinh(request.getGioiTinh());

		// Mã hóa mật khẩu trước khi lưu database.
		khachHang.setMatKhau(passwordEncoder.encode(request.getMatKhau()));

		// Gán vai trò CUSTOMER cho khách hàng.
		khachHang.setVaiTro(vaiTro);

		// Tài khoản mới được kích hoạt.
		khachHang.setTrangThai(true);

		return khachHang;
	}

	/**
	 * Lưu thông tin khách hàng vào database.
	 *
	 * @param khachHang đối tượng khách hàng cần lưu
	 * @return khách hàng sau khi được lưu
	 */
	private KhachHang luuKhachHang(KhachHang khachHang) {

		// Lưu khách hàng vào bảng KhachHang.
		return khachHangRepository.save(khachHang);
	}

	/**
	 * Chuyển thông tin Entity KhachHang thành DTO phản hồi.
	 *
	 * @param khachHang khách hàng đã được lưu vào database
	 * @return DTO chứa thông tin phản hồi
	 */
	private DangKyKhachHangResponseDTO taoPhanHoiDangKy(KhachHang khachHang) {

		// Khởi tạo DTO phản hồi.
		DangKyKhachHangResponseDTO response = new DangKyKhachHangResponseDTO();

		// Sao chép thông tin từ Entity sang DTO.
		response.setMaKH(khachHang.getMaKH());
		response.setHoTen(khachHang.getHoTen());
		response.setEmail(khachHang.getEmail());
		response.setSoDienThoai(khachHang.getSoDienThoai());
		response.setNgaySinh(khachHang.getNgaySinh());
		response.setGioiTinh(khachHang.getGioiTinh());

		// Chỉ trả về tên vai trò, không trả toàn bộ Entity VaiTro.
		response.setVaiTro(khachHang.getVaiTro().getTenVaiTro());

		return response;
	}

	/**
	 * Xử lý quy trình đăng ký tài khoản khách hàng.
	 *
	 * @param request dữ liệu đăng ký nhận từ Frontend
	 * @return thông tin khách hàng sau khi đăng ký thành công
	 */
	@Transactional
	public DangKyKhachHangResponseDTO dangKyKhachHang(DangKyKhachHangRequestDTO request) {

		// Bước 1: Kiểm tra email và số điện thoại.
		kiemTraThongTinDangKy(request);

		// Bước 2: Lấy vai trò CUSTOMER.
		VaiTro vaiTro = layVaiTroKhachHang();

		// Bước 3: Tạo đối tượng khách hàng và mã hóa mật khẩu.
		KhachHang khachHang = taoKhachHang(request, vaiTro);

		// Bước 4: Lưu khách hàng vào database.
		KhachHang khachHangDaLuu = luuKhachHang(khachHang);

		// Bước 5: Chuyển Entity thành DTO phản hồi.
		return taoPhanHoiDangKy(khachHangDaLuu);
	}

}
