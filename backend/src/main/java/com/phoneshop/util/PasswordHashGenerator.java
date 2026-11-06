package com.phoneshop.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Công cụ tạm thời tạo BCrypt Hash cho tài khoản thử nghiệm.
 */
@Component
public class PasswordHashGenerator implements CommandLineRunner {

	private final PasswordEncoder passwordEncoder;

	public PasswordHashGenerator(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) {

		// Mật khẩu thử nghiệm cho tài khoản STAFF.
		String matKhau = "Admin@123456";

		// Mã hóa mật khẩu bằng BCrypt.
		String hash = passwordEncoder.encode(matKhau);

		System.out.println("STAFF PASSWORD HASH: " + hash);
	}
}