package com.phoneshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity ánh xạ với bảng ChiTietGioHang. Lưu các phiên bản sản phẩm và số lượng
 * trong giỏ hàng.
 */
@Entity
@Table(name = "ChiTietGioHang")
public class ChiTietGioHang {
	// Khóa chính của chi tiết giỏ hàng
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaCTGH")
	private Integer maCTGH;

	// Giỏ hàng chứa sản phẩm
	@ManyToOne
	@JoinColumn(name = "MaGH", nullable = false)
	private GioHang gioHang;

	// Phiên bản sản phẩm được thêm vào giỏ
	@ManyToOne
	@JoinColumn(name = "MaPB", nullable = false)
	private PhienBan phienBan;

	// Số lượng sản phẩm
	@Column(name = "SoLuong", nullable = false)
	private Integer soLuong;

	// Constructor rỗng để JPA/Hibernate khởi tạo Entity
	public ChiTietGioHang() {
	}

	// Getter và Setter
	public Integer getMaCTGH() {
		return maCTGH;
	}

	public void setMaCTGH(Integer maCTGH) {
		this.maCTGH = maCTGH;
	}

	public GioHang getGioHang() {
		return gioHang;
	}

	public void setGioHang(GioHang gioHang) {
		this.gioHang = gioHang;
	}

	public PhienBan getPhienBan() {
		return phienBan;
	}

	public void setPhienBan(PhienBan phienBan) {
		this.phienBan = phienBan;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}
}
