package com.phoneshop.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entity ánh xạ với bảng ThanhToan. Lưu thông tin thanh toán của một đơn hàng.
 */
@Entity
@Table(name = "ThanhToan")
public class ThanhToan {
	// Khóa chính của thanh toán
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaTT")
	private Integer maTT;

	// Mỗi đơn hàng chỉ có một thông tin thanh toán
	@OneToOne
	@JoinColumn(name = "MaDH", nullable = false, unique = true)
	private DonHang donHang;

	// Phương thức thanh toán
	@Column(name = "PhuongThuc", nullable = false, length = 50)
	private String phuongThuc;

	// Số tiền thanh toán
	@Column(name = "SoTien", nullable = false, precision = 15, scale = 2)
	private BigDecimal soTien;

	// Trạng thái thanh toán
	@Column(name = "TrangThai", nullable = false, length = 50)
	private String trangThai;

	// Thời điểm thanh toán
	@Column(name = "NgayThanhToan")
	private LocalDateTime ngayThanhToan;

	// Constructor rỗng để JPA/Hibernate khởi tạo Entity
	public ThanhToan() {
	}

	// Getter và Setter
	public Integer getMaTT() {
		return maTT;
	}

	public void setMaTT(Integer maTT) {
		this.maTT = maTT;
	}

	public DonHang getDonHang() {
		return donHang;
	}

	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
	}

	public String getPhuongThuc() {
		return phuongThuc;
	}

	public void setPhuongThuc(String phuongThuc) {
		this.phuongThuc = phuongThuc;
	}

	public BigDecimal getSoTien() {
		return soTien;
	}

	public void setSoTien(BigDecimal soTien) {
		this.soTien = soTien;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public LocalDateTime getNgayThanhToan() {
		return ngayThanhToan;
	}

	public void setNgayThanhToan(LocalDateTime ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}
}
