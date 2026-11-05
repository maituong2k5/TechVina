
package com.phoneshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity đại diện cho bảng NhomThongSo. Lưu các nhóm thông số kỹ thuật của sản
 * phẩm.
 */
@Entity
@Table(name = "nhomthongso")
public class NhomThongSo {

	// Khóa chính, tự động tăng
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaNhom")
	private Integer maNhom;

	// Tên nhóm thông số
	@Column(name = "TenNhom", nullable = false, unique = true, length = 100)
	private String tenNhom;

	// Thứ tự hiển thị nhóm trên giao diện
	@Column(name = "ThuTuHienThi", nullable = false)
	private Integer thuTuHienThi = 0;

	// Constructor mặc định dành cho JPA
	public NhomThongSo() {
	}

	// Getter và Setter
	public Integer getMaNhom() {
		return maNhom;
	}

	public void setMaNhom(Integer maNhom) {
		this.maNhom = maNhom;
	}

	public String getTenNhom() {
		return tenNhom;
	}

	public void setTenNhom(String tenNhom) {
		this.tenNhom = tenNhom;
	}

	public Integer getThuTuHienThi() {
		return thuTuHienThi;
	}

	public void setThuTuHienThi(Integer thuTuHienThi) {
		this.thuTuHienThi = thuTuHienThi;
	}
}
