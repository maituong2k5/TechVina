package com.phoneshop.dto;

import java.math.BigDecimal;

/**
 * DTO chứa thông tin phiên bản sản phẩm phục vụ chức năng mua hàng.
 */
public class PhienBanMuaHangDTO {

	// Mã phiên bản sản phẩm
	private Integer maPB;

	// Mã SKU của phiên bản
	private String sku;

	// Dung lượng RAM (GB)
	private Integer ram;

	// Dung lượng ROM (GB)
	private Integer rom;

	// Tên màu sắc
	private String tenMau;

	// Giá bán hiện tại
	private BigDecimal gia;

	// Tổng số lượng tồn kho tại tất cả các kho
	private Long soLuongTon;

	/**
	 * Constructor khởi tạo DTO với đầy đủ thông tin phiên bản.
	 */
	public PhienBanMuaHangDTO(Integer maPB, String sku, Integer ram, Integer rom, String tenMau, BigDecimal gia,
			Long soLuongTon) {

		this.maPB = maPB;
		this.sku = sku;
		this.ram = ram;
		this.rom = rom;
		this.tenMau = tenMau;
		this.gia = gia;
		this.soLuongTon = soLuongTon;
	}

	// Getter và Setter cho maPB
	public Integer getMaPB() {
		return maPB;
	}

	public void setMaPB(Integer maPB) {
		this.maPB = maPB;
	}

	// Getter và Setter cho sku
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	// Getter và Setter cho ram
	public Integer getRam() {
		return ram;
	}

	public void setRam(Integer ram) {
		this.ram = ram;
	}

	// Getter và Setter cho rom
	public Integer getRom() {
		return rom;
	}

	public void setRom(Integer rom) {
		this.rom = rom;
	}

	// Getter và Setter cho tenMau
	public String getTenMau() {
		return tenMau;
	}

	public void setTenMau(String tenMau) {
		this.tenMau = tenMau;
	}

	// Getter và Setter cho gia
	public BigDecimal getGia() {
		return gia;
	}

	public void setGia(BigDecimal gia) {
		this.gia = gia;
	}

	// Getter và Setter cho soLuongTon
	public Long getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(Long soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
}