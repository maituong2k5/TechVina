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
 * Entity ánh xạ với bảng KhachHang. Lưu thông tin tài khoản và thông tin cơ bản
 * của khách hàng.
 */
@Entity
@Table(name = "khachhang")
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

	/**
	 * Định danh duy nhất của khách hàng trên Amazon Cognito.
	 *
	 * Sử dụng CognitoSub để liên kết tài khoản Cognito với bản ghi khách hàng trong
	 * MySQL.
	 *
	 * Cho phép null để giữ tương thích với dữ liệu cũ chưa được liên kết với
	 * Cognito.
	 */
	@Column(name = "CognitoSub", unique = true, length = 36)
	private String cognitoSub;

	/**
	 * Số điện thoại khách hàng.
	 *
	 * Cho phép NULL vì khách hàng đăng ký qua Amazon Cognito có thể chỉ cung cấp
	 * email.
	 */
	@Column(name = "SoDienThoai", length = 15, unique = true, nullable = true)
	private String soDienThoai;

	/**
	 * Mật khẩu của hệ thống đăng nhập cũ.
	 *
	 * Cho phép NULL đối với tài khoản sử dụng Cognito. Không lưu mật khẩu Cognito
	 * vào MySQL.
	 *
	 */
	@Column(name = "MatKhau", length = 255, nullable = true)
	private String matKhau;

	// Ngày sinh
	@Column(name = "NgaySinh")
	private LocalDate ngaySinh;

	// Giới tính và trạng thái tài khoản
	@Column(name = "GioiTinh", length = 10)
	private String gioiTinh;

	@Column(name = "TrangThai", nullable = false)
	private Boolean trangThai = true;

	// Nhiều khách hàng có thể cùng một vai trò CUSTOMER.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaVaiTro", nullable = false)
	private VaiTro vaiTro;

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

	// Lấy Cognito User ID của khách hàng.
	public String getCognitoSub() {
		return cognitoSub;
	}

	// Gán Cognito User ID cho khách hàng.
	public void setCognitoSub(String cognitoSub) {
		this.cognitoSub = cognitoSub;
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

	public VaiTro getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(VaiTro vaiTro) {
		this.vaiTro = vaiTro;
	}
}
