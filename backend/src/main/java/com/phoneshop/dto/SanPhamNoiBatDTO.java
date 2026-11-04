package com.phoneshop.dto;

import java.math.BigDecimal;

/**
 * DTO chứa thông tin sản phẩm nổi bật dùng để trả dữ liệu cho Frontend.
 */
public class SanPhamNoiBatDTO {
	// Thông tin sản phẩm
	private Integer maSP;
	private String tenSP;

	// Mã phiên bản sản phẩm
	private Integer maPB;

	// Thông tin thương hiệu và danh mục
	private String tenHang;
	private String tenDanhMuc;

	// Ảnh đại diện của phiên bản
	private String anhDaiDien;

	// Thông tin giá bán

	private BigDecimal giaGoc;
	private BigDecimal giaSauGiam;
	private BigDecimal giaTriGiam;
	private BigDecimal phanTramGiam;

	// Cấu hình dung lượng
	private String ram;
	private String rom;

	// Tổng số lượng tồn kho
	private Long soLuongTon;

	// Constructor không tham số
	public SanPhamNoiBatDTO() {
	}

	// Constructor đầy đủ tham số
	public SanPhamNoiBatDTO(Integer maSP, Integer maPB, String tenSP, String tenHang, String tenDanhMuc,
			String anhDaiDien, BigDecimal giaGoc, BigDecimal giaSauGiam, BigDecimal giaTriGiam, BigDecimal phanTramGiam,
			String ram, String rom, Long soLuongTon) {

		this.maSP = maSP;
		this.maPB = maPB;
		this.tenSP = tenSP;

		this.tenHang = tenHang;
		this.tenDanhMuc = tenDanhMuc;
		this.anhDaiDien = anhDaiDien;

		this.giaGoc = giaGoc;
		this.giaSauGiam = giaSauGiam;
		this.giaTriGiam = giaTriGiam;
		this.phanTramGiam = phanTramGiam;

		this.ram = ram;
		this.rom = rom;
		this.soLuongTon = soLuongTon;
	}

	// Getter và Setter
	public Integer getMaSP() {
		return maSP;
	}

	public void setMaSP(Integer maSP) {
		this.maSP = maSP;
	}

	// Getter: Lấy mã phiên bản sản phẩm
	public Integer getMaPB() {
		return maPB;
	}

	// Setter: Gán mã phiên bản sản phẩm
	public void setMaPB(Integer maPB) {
		this.maPB = maPB;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getTenHang() {
		return tenHang;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}

	public String getAnhDaiDien() {
		return anhDaiDien;
	}

	public void setAnhDaiDien(String anhDaiDien) {
		this.anhDaiDien = anhDaiDien;
	}

	public BigDecimal getGiaGoc() {
		return giaGoc;
	}

	public void setGiaGoc(BigDecimal giaGoc) {
		this.giaGoc = giaGoc;
	}

	public BigDecimal getGiaSauGiam() {
		return giaSauGiam;
	}

	public void setGiaSauGiam(BigDecimal giaSauGiam) {
		this.giaSauGiam = giaSauGiam;
	}

	public BigDecimal getGiaTriGiam() {
		return giaTriGiam;
	}

	public void setGiaTriGiam(BigDecimal giaTriGiam) {
		this.giaTriGiam = giaTriGiam;
	}

	public BigDecimal getPhanTramGiam() {
		return phanTramGiam;
	}

	public void setPhanTramGiam(BigDecimal phanTramGiam) {
		this.phanTramGiam = phanTramGiam;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getRom() {
		return rom;
	}

	public void setRom(String rom) {
		this.rom = rom;
	}

	public Long getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(Long soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

}
