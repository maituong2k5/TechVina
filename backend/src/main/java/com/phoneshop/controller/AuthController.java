package com.phoneshop.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoneshop.dto.DangKyKhachHangRequestDTO;
import com.phoneshop.dto.DangKyKhachHangResponseDTO;
import com.phoneshop.dto.DangNhapRequestDTO;
import com.phoneshop.dto.DangNhapResponseDTO;
import com.phoneshop.exception.InvalidCredentialsException;
import com.phoneshop.service.AuthService;

import jakarta.validation.Valid;

/**
 * Controller tiếp nhận các API liên quan đến xác thực tài khoản.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	// Service xử lý nghiệp vụ xác thực.
	private final AuthService authService;

	// Thời hạn Refresh Token (milliseconds).
	private final long refreshExpiration;

	// Môi trường HTTPS hay HTTP.
	private final boolean cookieSecure;

	/**
	 * Constructor Injection: Spring tự động cung cấp AuthService.
	 */
	public AuthController(AuthService authService, @Value("${jwt.refresh-expiration}") long refreshExpiration,
			@Value("${app.cookie.secure:false}") boolean cookieSecure) {
		this.authService = authService;
		this.refreshExpiration = refreshExpiration;
		this.cookieSecure = cookieSecure;
	}

	/**
	 * Tạo HttpOnly Cookie chứa Refresh Token.
	 *
	 * @param refreshToken Refresh Token gốc.
	 * @return Cookie đã thiết lập các thuộc tính bảo mật.
	 */
	private ResponseCookie taoRefreshTokenCookie(String refreshToken) {

		return ResponseCookie.from("refreshToken", refreshToken)

				// Ngăn JavaScript truy cập Cookie.
				.httpOnly(true)

				// Chỉ gửi Cookie qua HTTPS khi cấu hình được bật.
				.secure(cookieSecure)

				// Giới hạn phạm vi Cookie cho các API xác thực.
				.path("/api/auth")

				// Thiết lập thời hạn Cookie.
				.maxAge(Duration.ofMillis(refreshExpiration))

				// Hạn chế Cookie được gửi trong các ngữ cảnh cross-site.
				.sameSite("Lax")

				.build();
	}

	/**
	 * API đăng ký tài khoản khách hàng.
	 *
	 * @param request dữ liệu đăng ký nhận từ Frontend
	 * @return thông tin khách hàng sau khi đăng ký thành công
	 */
	@PostMapping("/register")
	public ResponseEntity<DangKyKhachHangResponseDTO> dangKyKhachHang(
			@Valid @RequestBody DangKyKhachHangRequestDTO request) {

		// Gọi Service xử lý nghiệp vụ đăng ký.
		DangKyKhachHangResponseDTO response = authService.dangKyKhachHang(request);

		// Trả về HTTP 201 Created khi đăng ký thành công.
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * API đăng nhập tài khoản khách hàng.
	 *
	 * @param request dữ liệu đăng nhập nhận từ Frontend
	 * @return thông tin tài khoản và thiết lập Refresh Token Cookie
	 */
	@PostMapping("/login")
	public ResponseEntity<DangNhapResponseDTO> dangNhap(@Valid @RequestBody DangNhapRequestDTO request) {

		// Bước 1: Gọi Service xử lý đăng nhập.
		DangNhapResponseDTO response = authService.dangNhap(request);

		// Bước 2: Tạo HttpOnly Cookie chứa Refresh Token.
		ResponseCookie refreshTokenCookie = taoRefreshTokenCookie(response.getRefreshToken());

		// Bước 3: Trả Cookie thông qua HTTP Response Header.
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString()).body(response);
	}

	/**
	 * API làm mới Access Token bằng Refresh Token trong Cookie.
	 *
	 * @param refreshTokenGoc Refresh Token nhận từ HttpOnly Cookie.
	 * @return Access Token mới và cập nhật Refresh Token Cookie.
	 */
	@PostMapping("/refresh")
	public ResponseEntity<DangNhapResponseDTO> lamMoiToken(
			@CookieValue(name = "refreshToken", required = false) String refreshTokenGoc) {

		// Bước 1: Kiểm tra Cookie có tồn tại hay không.
		if (refreshTokenGoc == null || refreshTokenGoc.isBlank()) {
			throw new InvalidCredentialsException("Không tìm thấy Refresh Token.");
		}

		// Bước 2: Gọi Service xử lý làm mới Token.
		DangNhapResponseDTO response = authService.lamMoiToken(refreshTokenGoc);

		// Bước 3: Tạo Cookie chứa Refresh Token mới.
		ResponseCookie refreshTokenCookie = taoRefreshTokenCookie(response.getRefreshToken());

		// Bước 4: Trả Access Token mới và cập nhật Cookie.
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString()).body(response);
	}

	/**
	 * API đăng xuất tài khoản khách hàng.
	 *
	 * @param refreshTokenGoc Refresh Token nhận từ HttpOnly Cookie.
	 * @return Kết quả đăng xuất và xóa Refresh Token Cookie.
	 */
	@PostMapping("/logout")
	public ResponseEntity<Void> dangXuat(@CookieValue(name = "refreshToken", required = false) String refreshTokenGoc) {

		// Bước 1: Thu hồi Refresh Token trong Database.
		authService.dangXuat(refreshTokenGoc);

		// Bước 2: Tạo Cookie hết hạn để xóa Cookie hiện tại.
		ResponseCookie cookieXoaRefreshToken = ResponseCookie.from("refreshToken", "").httpOnly(true)
				.secure(cookieSecure).path("/api/auth").maxAge(Duration.ZERO).sameSite("Lax").build();

		// Bước 3: Trả Cookie hết hạn về Client.
		return ResponseEntity.noContent().header(HttpHeaders.SET_COOKIE, cookieXoaRefreshToken.toString()).build();
	}
}
