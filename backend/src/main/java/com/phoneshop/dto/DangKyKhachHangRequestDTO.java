
package com.phoneshop.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO nhận dữ liệu từ Frontend khi khách hàng đăng ký tài khoản.
 */
public class DangKyKhachHangRequestDTO {

	// Họ và tên khách hàng.
	@NotBlank(message = "Họ tên không được để trống")
	@Size(max = 30, message = "Họ tên không được vượt quá 30 ký tự")
	private String hoTen;

	// Email dùng để đăng nhập.
	@NotBlank(message = "Email không được để trống")
	@Email(message = "Email không đúng định dạng")
	@Size(max = 100, message = "Email không được vượt quá 100 ký tự")
	private String email;

	// Số điện thoại Việt Nam gồm 10 chữ số, bắt đầu bằng số 0.
	@NotBlank(message = "Số điện thoại không được để trống")
	@Pattern(regexp = "^0[0-9]{9}$", message = "Số điện thoại phải gồm 10 chữ số và bắt đầu bằng số 0")
	private String soDienThoai;

	// Ngày sinh khách hàng.
	private LocalDate ngaySinh;

	// Giới tính khách hàng.
	private String gioiTinh;

	// Mật khẩu đăng ký.
	@NotBlank(message = "Mật khẩu không được để trống")
	@Size(min = 8, max = 72, message = "Mật khẩu phải từ 8 đến 72 ký tự")
	private String matKhau;

	// Mật khẩu xác nhận.
	@NotBlank(message = "Xác nhận mật khẩu không được để trống")
	private String xacNhanMatKhau;

	/**
	 * Kiểm tra mật khẩu và mật khẩu xác nhận có trùng nhau không.
	 */
	@AssertTrue(message = "Mật khẩu xác nhận không khớp")
	public boolean isMatKhauXacNhanHopLe() {
		return matKhau != null && matKhau.equals(xacNhanMatKhau);
	}

	// Getter và Setter

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

	// Getter và Setter cho mật khẩu.
	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	// Getter và Setter cho mật khẩu xác nhận.
	public String getXacNhanMatKhau() {
		return xacNhanMatKhau;
	}

	public void setXacNhanMatKhau(String xacNhanMatKhau) {
		this.xacNhanMatKhau = xacNhanMatKhau;
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

}
