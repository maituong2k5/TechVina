
package com.phoneshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity đại diện cho bảng ChiTietThongSo. Lưu giá trị thông số kỹ thuật theo
 * từng sản phẩm.
 */
@Entity
@Table(name = "chitietthongso")
public class ChiTietThongSo {

	// Khóa chính, tự động tăng
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaCTTS")
	private Integer maCTTS;

	// Sản phẩm sở hữu thông số kỹ thuật này
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaSP", nullable = false)
	private SanPham sanPham;

	// Định nghĩa thông số kỹ thuật
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaThongSo", nullable = false)
	private ThongSoKyThuat thongSoKyThuat;

	// Giá trị cụ thể của thông số
	@Column(name = "GiaTri", nullable = false, columnDefinition = "TEXT")
	private String giaTri;

	// Constructor mặc định dành cho JPA
	public ChiTietThongSo() {
	}

	// Getter và Setter
	public Integer getMaCTTS() {
		return maCTTS;
	}

	public void setMaCTTS(Integer maCTTS) {
		this.maCTTS = maCTTS;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public ThongSoKyThuat getThongSoKyThuat() {
		return thongSoKyThuat;
	}

	public void setThongSoKyThuat(ThongSoKyThuat thongSoKyThuat) {
		this.thongSoKyThuat = thongSoKyThuat;
	}

	public String getGiaTri() {
		return giaTri;
	}

	public void setGiaTri(String giaTri) {
		this.giaTri = giaTri;
	}
}
