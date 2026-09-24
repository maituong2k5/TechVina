package com.phoneshop.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity ánh xạ với bảng ChiTietPhieuNhap. Lưu thông tin chi tiết các sản phẩm
 * trong một phiếu nhập.
 */
@Entity
@Table(name = "ChiTietPhieuNhap")
public class ChiTietPhieuNhap {
	// Khóa chính của chi tiết phiếu nhập
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaCTPN")
	private Integer maCTPN;

	// Phiếu nhập chứa sản phẩm này
	@ManyToOne
	@JoinColumn(name = "MaPN", nullable = false)
	private PhieuNhap phieuNhap;

	// Phiên bản sản phẩm được nhập
	@ManyToOne
	@JoinColumn(name = "MaPB", nullable = false)
	private PhienBan phienBan;

	// Số lượng sản phẩm nhập
	@Column(name = "SoLuong", nullable = false)
	private Integer soLuong;

	// Giá nhập của một sản phẩm
	@Column(name = "GiaNhap", nullable = false, precision = 15, scale = 2)
	private BigDecimal giaNhap;

	// Constructor rỗng để JPA/Hibernate khởi tạo Entity
	public ChiTietPhieuNhap() {
	}

	// Getter và Setter
	public Integer getMaCTPN() {
		return maCTPN;
	}

	public void setMaCTPN(Integer maCTPN) {
		this.maCTPN = maCTPN;
	}

	public PhieuNhap getPhieuNhap() {
		return phieuNhap;
	}

	public void setPhieuNhap(PhieuNhap phieuNhap) {
		this.phieuNhap = phieuNhap;
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

	public BigDecimal getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(BigDecimal giaNhap) {
		this.giaNhap = giaNhap;
	}
}
