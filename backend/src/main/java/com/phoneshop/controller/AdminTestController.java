package com.phoneshop.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller dùng để kiểm thử quyền truy cập ADMIN.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminTestController {

	/**
	 * API chỉ tài khoản ADMIN mới được phép truy cập.
	 */
	@GetMapping("/test")
	public Map<String, String> kiemTraQuyenAdmin() {
		return Map.of("message", "Bạn đã truy cập thành công API ADMIN!", "role", "ADMIN");
	}
}