package com.phoneshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity ánh xạ với bảng TrangThaiDonHang. Lưu các trạng thái được sử dụng
 * trong quá trình xử lý đơn hàng.
 */
@Entity
@Table(name = "TrangThaiDonHang")
public class TrangThaiDonHang {
	// Khóa chính của trạng thái đơn hàng
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaTrangThai")
	private Integer maTrangThai;

	// Tên trạng thái đơn hàng
	@Column(name = "TenTrangThai", nullable = false, unique = true, length = 50)
	private String tenTrangThai;

	// Constructor rỗng để JPA/Hibernate khởi tạo Entity
	public TrangThaiDonHang() {
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
