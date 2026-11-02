package com.phoneshop.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity ánh xạ với bảng KhachHang. Lưu thông tin tài khoản và thông tin cơ bản
 * của khách hàng.
 */
@Entity
@Table(name = "KhachHang")
public class KhachHang {
	// Khóa chính của khách hàng
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaKH")
	private Integer maKH;

	// Thông tin cá nhân
	@Column(name = "HoTen", nullable = false, length = 100)
	private String hoTen;

	@Column(name = "Email", nullable = false, unique = true, length = 100)
	private String email;

	@Column(name = "SoDienThoai", nullable = false, unique = true, length = 15)
	private String soDienThoai;

	// Thông tin tài khoản
	@Column(name = "MatKhau", nullable = false, length = 255)
	private String matKhau;

	// Ngày sinh
	@Column(name = "NgaySinh")
	private LocalDate ngaySinh;

	// Giới tính và trạng thái tài khoản
	@Column(name = "GioiTinh", length = 10)
	private String gioiTinh;

	@Column(name = "TrangThai", nullable = false)
	private Boolean trangThai;

	// Constructor rỗng để JPA/Hibernate khởi tạo Entity
	public KhachHang() {
	}

	// Getter và Setter
	public Integer getMaKH() {
		return maKH;
	}

	public void setMaKH(Integer maKH) {
		this.maKH = maKH;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}
}
