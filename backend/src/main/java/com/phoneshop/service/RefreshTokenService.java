package com.phoneshop.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HexFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phoneshop.entity.KhachHang;
import com.phoneshop.entity.NhanVien;
import com.phoneshop.entity.RefreshToken;
import com.phoneshop.exception.InvalidCredentialsException;
import com.phoneshop.repository.KhachHangRepository;
import com.phoneshop.repository.NhanVienRepository;
import com.phoneshop.repository.RefreshTokenRepository;

/**
 * Service xử lý nghiệp vụ Refresh Token.
 *
 * Chịu trách nhiệm tạo, kiểm tra, xoay vòng và thu hồi Refresh Token.
 */
@Service
public class RefreshTokenService {
	// Repository quản lý dữ liệu Refresh Token.
	private final RefreshTokenRepository refreshTokenRepository;

	// Repository quản lý dữ liệu khách hàng.
	private final KhachHangRepository khachHangRepository;

	// Repository quản lý dữ liệu nhân viên.
	private final NhanVienRepository nhanVienRepository;

	// Thời hạn Refresh Token (milliseconds).
	private final long refreshExpiration;

	// Bộ sinh số ngẫu nhiên an toàn cho Refresh Token.
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();

	/**
	 * Constructor Injection.
	 *
	 * Spring tự động cung cấp các Repository cần thiết.
	 */
	public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, KhachHangRepository khachHangRepository,
			NhanVienRepository nhanVienRepository, @Value("${jwt.refresh-expiration}") long refreshExpiration) {

		this.refreshTokenRepository = refreshTokenRepository;
		this.khachHangRepository = khachHangRepository;
		this.nhanVienRepository = nhanVienRepository;
		this.refreshExpiration = refreshExpiration;
		// Kiểm tra thời hạn Refresh Token phải lớn hơn 0.
		if (refreshExpiration <= 0) {
			throw new IllegalStateException("Thời hạn Refresh Token phải lớn hơn 0");
		}
	}

	/**
	 * Tạo Refresh Token ngẫu nhiên.
	 *
	 * Sử dụng SecureRandom để đảm bảo tính bảo mật.
	 *
	 * @return Refresh Token dạng chuỗi Base64 URL-safe.
	 */
	private String taoRefreshToken() {

		// Tạo mảng byte ngẫu nhiên gồm 64 byte.
		byte[] tokenBytes = new byte[64];
		SECURE_RANDOM.nextBytes(tokenBytes);

		// Chuyển thành chuỗi Base64 URL-safe, không có padding.
		return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
	}

	/**
	 * Hash Refresh Token bằng thuật toán SHA-256.
	 *
	 * @param token Refresh Token gốc.
	 * @return Chuỗi Hash dạng hexadecimal.
	 */
	private String hashRefreshToken(String token) {

		try {
			// Khởi tạo thuật toán SHA-256.
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// Chuyển Token thành mảng byte và thực hiện Hash.
			byte[] hashBytes = digest.digest(token.getBytes(StandardCharsets.UTF_8));

			// Chuyển mảng byte thành chuỗi hexadecimal.
			return HexFormat.of().formatHex(hashBytes);

		} catch (NoSuchAlgorithmException exception) {
			throw new IllegalStateException("Không hỗ trợ thuật toán SHA-256", exception);
		}
	}

	/**
	 * Tạo và lưu Refresh Token cho khách hàng.
	 *
	 * Database chỉ lưu giá trị Hash. Token gốc được trả về để gửi cho Client qua
	 * HttpOnly Cookie.
	 *
	 * @param maKH Mã khách hàng.
	 * @return Refresh Token gốc.
	 */
	@Transactional
	public String taoVaLuuRefreshToken(Integer maKH) {

		// Tìm khách hàng theo mã.
		KhachHang khachHang = khachHangRepository.findById(maKH)
				.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khách hàng"));

		// Sinh Refresh Token ngẫu nhiên.
		String refreshTokenGoc = taoRefreshToken();

		// Hash Token trước khi lưu Database.
		String tokenHash = hashRefreshToken(refreshTokenGoc);

		// Khởi tạo Entity RefreshToken.
		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setKhachHang(khachHang);
		refreshToken.setTokenHash(tokenHash);

		// Lấy thời điểm hiện tại.
		LocalDateTime thoiDiemHienTai = LocalDateTime.now();

		// Thiết lập thời gian tạo Token.
		refreshToken.setNgayTao(thoiDiemHienTai);

		// Thiết lập thời gian hết hạn Token (dựa trên cấu hình).
		refreshToken.setNgayHetHan(thoiDiemHienTai.plusSeconds(refreshExpiration / 1000));

		// Lưu Refresh Token vào Database.
		refreshTokenRepository.save(refreshToken);

		// Chỉ trả Token gốc cho tầng Controller.
		return refreshTokenGoc;
	}

	/**
	 * Tạo và lưu Refresh Token cho nhân viên.
	 *
	 * @param maNhanVien Mã nhân viên.
	 * @return Refresh Token gốc.
	 */
	@Transactional
	public String taoVaLuuRefreshTokenChoNhanVien(Integer maNhanVien) {

		// Tìm nhân viên theo mã.
		NhanVien nhanVien = nhanVienRepository.findById(maNhanVien)
				.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhân viên"));

		// Sinh Refresh Token ngẫu nhiên.
		String refreshTokenGoc = taoRefreshToken();

		// Hash Token trước khi lưu Database.
		String tokenHash = hashRefreshToken(refreshTokenGoc);

		// Khởi tạo Entity RefreshToken.
		RefreshToken refreshToken = new RefreshToken();

		// Gắn Refresh Token với nhân viên.
		refreshToken.setNhanVien(nhanVien);
		refreshToken.setTokenHash(tokenHash);

		// Thiết lập thời gian tạo và hết hạn.
		LocalDateTime thoiDiemHienTai = LocalDateTime.now();

		refreshToken.setNgayTao(thoiDiemHienTai);
		refreshToken.setNgayHetHan(thoiDiemHienTai.plusSeconds(refreshExpiration / 1000));

		// Lưu Refresh Token vào Database.
		refreshTokenRepository.save(refreshToken);

		// Trả Token gốc để gửi cho Client qua HttpOnly Cookie.
		return refreshTokenGoc;
	}

	/**
	 * Kiểm tra Refresh Token có hợp lệ hay không.
	 *
	 * @param refreshTokenGoc Refresh Token gốc nhận từ Cookie.
	 * @return RefreshToken nếu token còn hạn và chưa bị thu hồi.
	 */
	public RefreshToken kiemTraRefreshToken(String refreshTokenGoc) {
		// Kiểm tra Refresh Token đầu vào.
		if (refreshTokenGoc == null || refreshTokenGoc.isBlank()) {
			throw new InvalidCredentialsException("Refresh Token không được cung cấp.");
		}

		// Chuyển Refresh Token gốc thành SHA-256 Hash.
		String tokenHash = hashRefreshToken(refreshTokenGoc);

		// Lấy thời điểm hiện tại.
		LocalDateTime thoiDiemHienTai = LocalDateTime.now();

		// Tìm token còn hạn và chưa bị thu hồi trong Database.
		return refreshTokenRepository.findByTokenHashAndNgayThuHoiIsNullAndNgayHetHanAfter(tokenHash, thoiDiemHienTai)
				.orElseThrow(() -> new InvalidCredentialsException("Refresh Token không hợp lệ hoặc đã hết hạn."));
	}

	/**
	 * Thu hồi Refresh Token bằng cách cập nhật thời điểm thu hồi.
	 *
	 * @param refreshTokenGoc Refresh Token gốc nhận từ Cookie.
	 */
	@Transactional
	public void thuHoiRefreshToken(String refreshTokenGoc) {

		// Kiểm tra token trước khi thu hồi.
		RefreshToken refreshToken = kiemTraRefreshToken(refreshTokenGoc);

		// Ghi nhận thời điểm thu hồi.
		refreshToken.setNgayThuHoi(LocalDateTime.now());

		// Lưu trạng thái mới vào Database.
		refreshTokenRepository.save(refreshToken);
	}

	/**
	 * Xoay vòng Refresh Token cho khách hàng hoặc nhân viên.
	 *
	 * @param refreshTokenGoc Refresh Token hiện tại.
	 * @return Refresh Token mới.
	 */
	@Transactional
	public String xoayVongRefreshToken(String refreshTokenGoc) {

		// Bước 1: Kiểm tra Refresh Token hiện tại.
		RefreshToken tokenCu = kiemTraRefreshToken(refreshTokenGoc);

		// Bước 2: Thu hồi Refresh Token cũ.
		tokenCu.setNgayThuHoi(LocalDateTime.now());
		refreshTokenRepository.save(tokenCu);

		// Bước 3: Tạo Refresh Token mới theo chủ sở hữu.
		if (tokenCu.getKhachHang() != null) {

			// Trường hợp tài khoản CUSTOMER.
			Integer maKH = tokenCu.getKhachHang().getMaKH();

			return taoVaLuuRefreshToken(maKH);
		}

		if (tokenCu.getNhanVien() != null) {

			// Trường hợp tài khoản STAFF hoặc ADMIN.
			Integer maNhanVien = tokenCu.getNhanVien().getMaNhanVien();

			return taoVaLuuRefreshTokenChoNhanVien(maNhanVien);
		}

		// Trường hợp dữ liệu chủ sở hữu không hợp lệ.
		throw new IllegalStateException("Refresh Token không có tài khoản sở hữu hợp lệ.");
	}
}