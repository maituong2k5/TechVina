
package com.phoneshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO nhận dữ liệu từ Frontend khi người dùng đăng nhập.
 */
public class DangNhapRequestDTO {

	// Email dùng để đăng nhập.
	@NotBlank(message = "Email không được để trống")
	@Email(message = "Email không đúng định dạng")
	@Size(max = 100, message = "Email không được vượt quá 100 ký tự")
	private String email;

	// Mật khẩu đăng nhập.
	@NotBlank(message = "Mật khẩu không được để trống")
	@Size(max = 72, message = "Mật khẩu không được vượt quá 72 ký tự")
	private String matKhau;

	// Getter và Setter cho email.
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Getter và Setter cho mật khẩu.
	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

}
