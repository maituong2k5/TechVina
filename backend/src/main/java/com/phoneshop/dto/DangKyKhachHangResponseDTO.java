package com.phoneshop.dto;

import java.time.LocalDate;

/**
 * DTO trả kết quả đăng ký tài khoản khách hàng về Frontend.
 */
public class DangKyKhachHangResponseDTO {

	// Mã khách hàng.
	private Integer maKH;

	// Họ tên khách hàng.
	private String hoTen;

	// Email khách hàng.
	private String email;

	// Số điện thoại khách hàng.
	private String soDienThoai;

	// Ngày sinh khách hàng.
	private LocalDate ngaySinh;

	// Giới tính khách hàng.
	private String gioiTinh;

	// Tên vai trò của tài khoản.
	private String vaiTro;

	// Constructor rỗng.
	public DangKyKhachHangResponseDTO() {
	}

	// Constructor đầy đủ tham số.
	public DangKyKhachHangResponseDTO(Integer maKH, String hoTen, String email, String soDienThoai, String vaiTro) {

		this.maKH = maKH;
		this.hoTen = hoTen;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.vaiTro = vaiTro;
	}

	// Getter và Setter cho mã khách hàng.
	public Integer getMaKH() {
		return maKH;
	}

	public void setMaKH(Integer maKH) {
		this.maKH = maKH;
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

	// Getter và Setter cho số điện thoại.
	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	// Getter và Setter cho ngày sinh.
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	// Getter và Setter cho giới tính.
	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	// Getter và Setter cho vai trò.
	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}

}