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

@Entity
@Table(name = "chitietgiamgia")
public class ChiTietGiamGia {

	// Khóa chính của bảng ChiTietGiamGia
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaCTGG")
	private Integer maCTGG;

	// Chương trình giảm giá được áp dụng
	@ManyToOne
	@JoinColumn(name = "MaGiamGia", nullable = false)
	private GiamGia giamGia;

	// Phiên bản sản phẩm được áp dụng giảm giá
	@ManyToOne
	@JoinColumn(name = "MaPB", nullable = false)
	private PhienBan phienBan;

	// Mức giảm riêng cho phiên bản này (nếu có)
	// Nếu NULL thì sử dụng GiaTriGiam của bảng GiamGia
	@Column(name = "GiaTriGiamRieng", precision = 15, scale = 2)
	private BigDecimal giaTriGiamRieng;

	// Constructor mặc định bắt buộc cho JPA
	public ChiTietGiamGia() {
	}

	// Getters và Setters
	public Integer getMaCTGG() {
		return maCTGG;
	}

	public void setMaCTGG(Integer maCTGG) {
		this.maCTGG = maCTGG;
	}

	public GiamGia getGiamGia() {
		return giamGia;
	}

	public void setGiamGia(GiamGia giamGia) {
		this.giamGia = giamGia;
	}

	public PhienBan getPhienBan() {
		return phienBan;
	}

	public void setPhienBan(PhienBan phienBan) {
		this.phienBan = phienBan;
	}

	public BigDecimal getGiaTriGiamRieng() {
		return giaTriGiamRieng;
	}

	public void setGiaTriGiamRieng(BigDecimal giaTriGiamRieng) {
		this.giaTriGiamRieng = giaTriGiamRieng;
	}
}