
package com.phoneshop.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Entity quản lý chương trình giảm giá.
 */
@Entity
@Table(name = "giamgia", uniqueConstraints = {
		@UniqueConstraint(name = "uk_giamgia_phienban", columnNames = { "MaGiamGia", "MaPB" }) })
public class GiamGia {

	// Khóa chính của bảng GiamGia
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaGiamGia")
	private Integer maGiamGia;

	// Tên chương trình giảm giá
	@Column(name = "TenGiamGia", nullable = false, length = 100)
	private String tenGiamGia;

	// Loại giảm giá: FIXED hoặc PERCENT
	@Column(name = "LoaiGiamGia", nullable = false, length = 20)
	private String loaiGiamGia;

	// Giá trị giảm của chương trình
	@Column(name = "GiaTriGiam", precision = 15, scale = 2, nullable = false)
	private BigDecimal giaTriGiam;

	// Ngày bắt đầu chương trình
	@Column(name = "NgayBatDau", nullable = false)
	private LocalDateTime ngayBatDau;

	// Ngày kết thúc chương trình
	@Column(name = "NgayKetThuc", nullable = false)
	private LocalDateTime ngayKetThuc;

	@Column(name = "TrangThai", nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean trangThai = true;

	// Constructor không tham số
	public GiamGia() {
	}

	// Getter và Setter
	public Integer getMaGiamGia() {
		return maGiamGia;
	}

	public void setMaGiamGia(Integer maGiamGia) {
		this.maGiamGia = maGiamGia;
	}

	public String getTenGiamGia() {
		return tenGiamGia;
	}

	public void setTenGiamGia(String tenGiamGia) {
		this.tenGiamGia = tenGiamGia;
	}

	public String getLoaiGiamGia() {
		return loaiGiamGia;
	}

	public void setLoaiGiamGia(String loaiGiamGia) {
		this.loaiGiamGia = loaiGiamGia;
	}

	public BigDecimal getGiaTriGiam() {
		return giaTriGiam;
	}

	public void setGiaTriGiam(BigDecimal giaTriGiam) {
		this.giaTriGiam = giaTriGiam;
	}

	public LocalDateTime getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(LocalDateTime ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public LocalDateTime getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}
}
