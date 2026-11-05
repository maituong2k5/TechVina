
package com.phoneshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity đại diện cho bảng VaiTro trong database. Quản lý các vai trò CUSTOMER,
 * STAFF và ADMIN.
 */
@Entity
@Table(name = "vaitro")
public class VaiTro {

	// Khóa chính của vai trò.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaVaiTro")
	private Integer maVaiTro;

	// Tên vai trò: CUSTOMER, STAFF hoặc ADMIN.
	@Column(name = "TenVaiTro", length = 30, nullable = false, unique = true)
	private String tenVaiTro;

	// Mô tả chức năng của vai trò.
	@Column(name = "MoTa", length = 255)
	private String moTa;

	// Constructor không tham số phục vụ JPA.
	public VaiTro() {
	}

	// Constructor có tham số.
	public VaiTro(String tenVaiTro, String moTa) {
		this.tenVaiTro = tenVaiTro;
		this.moTa = moTa;
	}

	public Integer getMaVaiTro() {
		return maVaiTro;
	}

	public void setMaVaiTro(Integer maVaiTro) {
		this.maVaiTro = maVaiTro;
	}

	public String getTenVaiTro() {
		return tenVaiTro;
	}

	public void setTenVaiTro(String tenVaiTro) {
		this.tenVaiTro = tenVaiTro;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
}
