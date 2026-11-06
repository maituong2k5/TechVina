package com.phoneshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DangNhapResponseDTO {

	// Mã tài khoản
	private Integer maTaiKhoan;

	private String hoTen;

	private String email;

	private String vaiTro;

	// Access Token được cấp sau khi đăng nhập thành công.
	private String accessToken;

	// Refresh Token dùng nội bộ để Controller thiết lập HttpOnly Cookie.
	@JsonIgnore
	private String refreshToken;

	// Getter và Setter cho mã tài khoản.
	public Integer getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(Integer maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	// Getter và Setter cho họ tên.
	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	// Getter và Setter cho email.
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Getter và Setter cho vai trò.
	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}

	// Getter và Setter cho Access Token.
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
