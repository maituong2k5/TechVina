package com.phoneshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity ánh xạ với bảng TrangThaiPhieuNhap. Dùng để lưu các trạng thái của
 * phiếu nhập.
 */
@Entity
@Table(name = "TrangThaiPhieuNhap")
public class TrangThaiPhieuNhap {
	// Khóa chính của trạng thái phiếu nhập
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaTrangThai")
	private Integer maTrangThai;

	// Tên trạng thái, không được để trống và không được trùng
	@Column(name = "TenTrangThai", nullable = false, unique = true, length = 30)
	private String tenTrangThai;

	// Constructor rỗng để JPA/Hibernate khởi tạo Entity
	public TrangThaiPhieuNhap() {
	}

	// Getter và Setter
	public Integer getMaTrangThai() {
		return maTrangThai;
	}

	public void setMaTrangThai(Integer maTrangThai) {
		this.maTrangThai = maTrangThai;
	}

	public String getTenTrangThai() {
		return tenTrangThai;
	}

	public void setTenTrangThai(String tenTrangThai) {
		this.tenTrangThai = tenTrangThai;
	}
}
