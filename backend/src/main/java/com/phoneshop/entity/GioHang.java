package com.phoneshop.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity ánh xạ với bảng GioHang. Lưu thông tin giỏ hàng của khách hàng.
 */
@Entity
@Table(name = "GioHang")
public class GioHang {
	// Khóa chính của giỏ hàng
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaGH")
	private Integer maGH;

	// Khách hàng sở hữu giỏ hàng
	@ManyToOne
	@JoinColumn(name = "MaKH", nullable = false, unique = true)
	private KhachHang khachHang;

	// Thời gian tạo và cập nhật giỏ hàng
	@Column(name = "NgayTao", nullable = false)
	private LocalDateTime ngayTao;

	@Column(name = "NgayCapNhat", nullable = false)
	private LocalDateTime ngayCapNhat;

	// Constructor rỗng để JPA/Hibernate khởi tạo Entity
	public GioHang() {
	}

	// Getter và Setter
	public Integer getMaGH() {
		return maGH;
	}

	public void setMaGH(Integer maGH) {
		this.maGH = maGH;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public LocalDateTime getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDateTime ngayTao) {
		this.ngayTao = ngayTao;
	}

	public LocalDateTime getNgayCapNhat() {
		return ngayCapNhat;
	}

	public void setNgayCapNhat(LocalDateTime ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}
}
