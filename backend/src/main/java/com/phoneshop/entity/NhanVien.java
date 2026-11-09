package com.phoneshop.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity ánh xạ với bảng NhanVien. Lưu thông tin tài khoản và thông tin cơ bản
 * của nhân viên.
 */
@Entity
@Table(name = "nhanvien")
public class NhanVien {
	// Khóa chính của nhân viên
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaNhanVien")
	private Integer maNhanVien;

	// Thông tin cá nhân
	@Column(name = "HoTen", nullable = false, length = 100)
	private String hoTen;

	@Column(name = "NgaySinh", nullable = false)
	private LocalDate ngaySinh;

	@Column(name = "GioiTinh", length = 20)
	private String gioiTinh;

	// Thông tin tài khoản
	@Column(name = "Email", nullable = false, unique = true, length = 100)
	private String email;

	/**
	 * Định danh duy nhất của nhân viên trên Amazon Cognito.
	 *
	 * Liên kết tài khoản STAFF hoặc ADMIN của Cognito với thông tin nhân viên trong
	 * MySQL.
	 */
	@Column(name = "CognitoSub", unique = true, length = 36)
	private String cognitoSub;

	@Column(name = "MatKhau", nullable = false, length = 255)
	private String matKhau;

	// Đường dẫn ảnh đại diện
	@Column(name = "DuongDanAnh", length = 255)
	private String duongDanAnh;

	// Trạng thái hoạt động của nhân viên
	@Column(name = "TrangThai", nullable = false)
	private Boolean trangThai = true;

	// Quan hệ nhiều nhân viên có thể cùng một vai trò STAFF hoặc ADMIN.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaVaiTro", nullable = false)
	private VaiTro vaiTro;

	// Constructor rỗng để JPA/Hibernate khởi tạo Entity
	public NhanVien() {
	}

	// Getter và Setter
	public Integer getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(Integer maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Lấy Cognito User ID của nhân viên.
	public String getCognitoSub() {
		return cognitoSub;
	}

	// Gán Cognito User ID cho nhân viên.
	public void setCognitoSub(String cognitoSub) {
		this.cognitoSub = cognitoSub;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getDuongDanAnh() {
		return duongDanAnh;
	}

	public void setDuongDanAnh(String duongDanAnh) {
		this.duongDanAnh = duongDanAnh;
	}

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}

	// Lấy vai trò của nhân viên.
	public VaiTro getVaiTro() {
		return vaiTro;
	}

	// Thiết lập vai trò cho nhân viên.
	public void setVaiTro(VaiTro vaiTro) {
		this.vaiTro = vaiTro;
	}
}
