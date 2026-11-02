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
 * Entity ánh xạ với bảng ChiTietDonHang. Lưu thông tin từng sản phẩm thuộc một
 * đơn hàng.
 */
@Entity
@Table(name = "ChiTietDonHang")
public class ChiTietDonHang {
	// Khóa chính của chi tiết đơn hàng
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaCTDH")
	private Integer maCTDH;

	// Đơn hàng chứa chi tiết này
	@ManyToOne
	@JoinColumn(name = "MaDH", nullable = false)
	private DonHang donHang;

	// Phiên bản sản phẩm được mua
	@ManyToOne
	@JoinColumn(name = "MaPB", nullable = false)
	private PhienBan phienBan;

	// Số lượng sản phẩm
	@Column(name = "SoLuong", nullable = false)
	private Integer soLuong;

	// Đơn giá tại thời điểm khách hàng mua
	@Column(name = "DonGia", nullable = false, precision = 15, scale = 2)
	private BigDecimal donGia;

	// Thành tiền của dòng sản phẩm
	@Column(name = "ThanhTien", nullable = false, precision = 15, scale = 2)
	private BigDecimal thanhTien;

	// Constructor rỗng để JPA/Hibernate khởi tạo Entity
	public ChiTietDonHang() {
	}

	// Getter và Setter
	public Integer getMaCTDH() {
		return maCTDH;
	}

	public void setMaCTDH(Integer maCTDH) {
		this.maCTDH = maCTDH;
	}

	public DonHang getDonHang() {
		return donHang;
	}

	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
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

	public BigDecimal getDonGia() {
		return donGia;
	}

	public void setDonGia(BigDecimal donGia) {
		this.donGia = donGia;
	}

	public BigDecimal getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(BigDecimal thanhTien) {
		this.thanhTien = thanhTien;
	}
}
