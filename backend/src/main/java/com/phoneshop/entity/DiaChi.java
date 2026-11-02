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
 * Entity ánh xạ với bảng DiaChi. Lưu các địa chỉ giao hàng của khách hàng.
 */
@Entity
@Table(name = "DiaChi")
public class DiaChi {
	// Khóa chính của địa chỉ
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaDiaChi")
	private Integer maDiaChi;

	// Khách hàng sở hữu địa chỉ này
	@ManyToOne
	@JoinColumn(name = "MaKH", nullable = false)
	private KhachHang khachHang;

	// Thông tin người nhận
	@Column(name = "TenNguoiNhan", nullable = false, length = 100)
	private String tenNguoiNhan;

	@Column(name = "SoDienThoai", nullable = false, length = 15)
	private String soDienThoai;

	// Thông tin địa chỉ
	@Column(name = "TinhThanh", nullable = false, length = 100)
	private String tinhThanh;

	@Column(name = "QuanHuyen", nullable = false, length = 100)
	private String quanHuyen;

	@Column(name = "PhuongXa", nullable = false, length = 100)
	private String phuongXa;

	@Column(name = "DiaChiChiTiet", nullable = false, length = 255)
	private String diaChiChiTiet;

	@Column(name = "MacDinh", nullable = false)
	private Boolean macDinh;

	// Constructor rỗng để JPA/Hibernate khởi tạo Entity
	public DiaChi() {
	}

	// Getter và Setter
	public Integer getMaDiaChi() {
		return maDiaChi;
	}

	public void setMaDiaChi(Integer maDiaChi) {
		this.maDiaChi = maDiaChi;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public String getTenNguoiNhan() {
		return tenNguoiNhan;
	}

	public void setTenNguoiNhan(String tenNguoiNhan) {
		this.tenNguoiNhan = tenNguoiNhan;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getTinhThanh() {
		return tinhThanh;
	}

	public void setTinhThanh(String tinhThanh) {
		this.tinhThanh = tinhThanh;
	}

	public String getQuanHuyen() {
		return quanHuyen;
	}

	public void setQuanHuyen(String quanHuyen) {
		this.quanHuyen = quanHuyen;
	}

	public String getPhuongXa() {
		return phuongXa;
	}

	public void setPhuongXa(String phuongXa) {
		this.phuongXa = phuongXa;
	}

	public String getDiaChiChiTiet() {
		return diaChiChiTiet;
	}

	public void setDiaChiChiTiet(String diaChiChiTiet) {
		this.diaChiChiTiet = diaChiChiTiet;
	}

	public Boolean getMacDinh() {
		return macDinh;
	}

	public void setMacDinh(Boolean macDinh) {
		this.macDinh = macDinh;
	}
}
