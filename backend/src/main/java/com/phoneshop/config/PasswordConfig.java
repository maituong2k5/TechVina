
package com.phoneshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Cấu hình mã hóa mật khẩu cho hệ thống.
 */
@Configuration
public class PasswordConfig {

	/**
	 * Khai báo PasswordEncoder để Spring quản lý. BCrypt giúp lưu mật khẩu dưới
	 * dạng chuỗi đã mã hóa.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
