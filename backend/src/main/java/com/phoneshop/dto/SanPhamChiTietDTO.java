
package com.phoneshop.dto;

/**
 * DTO chứa thông tin cơ bản của sản phẩm phục vụ trang chi tiết sản phẩm.
 */
public class SanPhamChiTietDTO {

	private Integer maSP;
	private String tenSP;
	private String tenHang;
	private String tenDanhMuc;

	// Constructor
	public SanPhamChiTietDTO(Integer maSP, String tenSP, String tenHang, String tenDanhMuc) {

		this.maSP = maSP;
		this.tenSP = tenSP;
		this.tenHang = tenHang;
		this.tenDanhMuc = tenDanhMuc;
	}

	// Getter
	public Integer getMaSP() {
		return maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public String getTenHang() {
		return tenHang;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	// Setter
	public void setMaSP(Integer maSP) {
		this.maSP = maSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}
}
