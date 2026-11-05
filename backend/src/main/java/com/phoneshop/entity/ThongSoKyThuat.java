
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
 * Entity đại diện cho bảng ThongSoKyThuat. Lưu danh sách các thông số thuộc
 * từng nhóm thông số.
 */
@Entity
@Table(name = "thongsokythuat")
public class ThongSoKyThuat {

	// Khóa chính, tự động tăng
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaThongSo")
	private Integer maThongSo;

	// Nhóm thông số mà thuộc tính này trực thuộc
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaNhom", nullable = false)
	private NhomThongSo nhomThongSo;

	// Tên thông số kỹ thuật
	@Column(name = "TenThongSo", nullable = false, length = 150)
	private String tenThongSo;

	// Đơn vị đo của thông số, có thể để trống
	@Column(name = "DonVi", length = 30)
	private String donVi;

	// Thứ tự hiển thị thông số
	@Column(name = "ThuTuHienThi", nullable = false)
	private Integer thuTuHienThi = 0;

	// Constructor mặc định dành cho JPA
	public ThongSoKyThuat() {
	}

	// Getter và Setter
	public Integer getMaThongSo() {
		return maThongSo;
	}

	public void setMaThongSo(Integer maThongSo) {
		this.maThongSo = maThongSo;
	}

	public NhomThongSo getNhomThongSo() {
		return nhomThongSo;
	}

	public void setNhomThongSo(NhomThongSo nhomThongSo) {
		this.nhomThongSo = nhomThongSo;
	}

	public String getTenThongSo() {
		return tenThongSo;
	}

	public void setTenThongSo(String tenThongSo) {
		this.tenThongSo = tenThongSo;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public Integer getThuTuHienThi() {
		return thuTuHienThi;
	}

	public void setThuTuHienThi(Integer thuTuHienThi) {
		this.thuTuHienThi = thuTuHienThi;
	}
}
