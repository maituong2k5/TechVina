package com.phoneshop.entity;

import java.math.BigDecimal;
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
 * Entity ánh xạ với bảng DonHang. Lưu thông tin đơn hàng và thông tin người
 * nhận tại thời điểm khách hàng đặt hàng.
 */
@Entity
@Table(name = "DonHang")
public class DonHang {
	// Khóa chính của đơn hàng
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaDH")
	private Integer maDH;

	// Khách hàng đặt đơn
	@ManyToOne
	@JoinColumn(name = "MaKH", nullable = false)
	private KhachHang khachHang;

	// Trạng thái hiện tại của đơn hàng
	@ManyToOne
	@JoinColumn(name = "MaTrangThai", nullable = false)
	private TrangThaiDonHang trangThaiDonHang;

	// Thông tin người nhận tại thời điểm đặt hàng
	@Column(name = "HoTenNguoiNhan", nullable = false, length = 100)
	private String hoTenNguoiNhan;

	@Column(name = "SoDienThoaiNguoiNhan", nullable = false, length = 15)
	private String soDienThoaiNguoiNhan;

	// Địa chỉ giao hàng được lưu dạng snapshot
	@Column(name = "TinhThanh", nullable = false, length = 100)
	private String tinhThanh;

	@Column(name = "QuanHuyen", nullable = false, length = 100)
	private String quanHuyen;

	@Column(name = "PhuongXa", nullable = false, length = 100)
	private String phuongXa;

	@Column(name = "DiaChiChiTiet", nullable = false, length = 255)
	private String diaChiChiTiet;

	// Tổng tiền của đơn hàng
	@Column(name = "TongTien", nullable = false, precision = 15, scale = 2)
	private BigDecimal tongTien;

	// Thời điểm đặt hàng
	@Column(name = "NgayDat", nullable = false)
	private LocalDateTime ngayDat;

	// Constructor rỗng để JPA/Hibernate khởi tạo Entity
	public DonHang() {
	}

	// Getter và Setter
	public Integer getMaDH() {
		return maDH;
	}

	public void setMaDH(Integer maDH) {
		this.maDH = maDH;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public TrangThaiDonHang getTrangThaiDonHang() {
		return trangThaiDonHang;
	}

	public void setTrangThaiDonHang(TrangThaiDonHang trangThaiDonHang) {
		this.trangThaiDonHang = trangThaiDonHang;
	}

	public String getHoTenNguoiNhan() {
		return hoTenNguoiNhan;
	}

	public void setHoTenNguoiNhan(String hoTenNguoiNhan) {
		this.hoTenNguoiNhan = hoTenNguoiNhan;
	}

	public String getSoDienThoaiNguoiNhan() {
		return soDienThoaiNguoiNhan;
	}

	public void setSoDienThoaiNguoiNhan(String soDienThoaiNguoiNhan) {
		this.soDienThoaiNguoiNhan = soDienThoaiNguoiNhan;
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

	public BigDecimal getTongTien() {
		return tongTien;
	}

	public void setTongTien(BigDecimal tongTien) {
		this.tongTien = tongTien;
	}

	public LocalDateTime getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(LocalDateTime ngayDat) {
		this.ngayDat = ngayDat;
	}
}
