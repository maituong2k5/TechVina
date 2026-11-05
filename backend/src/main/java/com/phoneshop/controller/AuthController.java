package com.phoneshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoneshop.dto.DangKyKhachHangRequestDTO;
import com.phoneshop.dto.DangKyKhachHangResponseDTO;
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

	/**
	 * Constructor Injection: Spring tự động cung cấp AuthService.
	 */
	public AuthController(AuthService authService) {
		this.authService = authService;
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

}
