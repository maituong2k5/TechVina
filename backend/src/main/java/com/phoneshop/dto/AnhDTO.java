
package com.phoneshop.dto;

/**
 * DTO chứa thông tin hình ảnh sản phẩm.
 */
public class AnhDTO {

	private Integer maAnh;
	private String duongDanAnh;
	private Integer thuTuHienThi;

	// Constructor
	public AnhDTO(Integer maAnh, String duongDanAnh, Integer thuTuHienThi) {

		this.maAnh = maAnh;
		this.duongDanAnh = duongDanAnh;
		this.thuTuHienThi = thuTuHienThi;
	}

	// Getter
	public Integer getMaAnh() {
		return maAnh;
	}

	public String getDuongDanAnh() {
		return duongDanAnh;
	}

	public Integer getThuTuHienThi() {
		return thuTuHienThi;
	}

	// Setter
	public void setMaAnh(Integer maAnh) {
		this.maAnh = maAnh;
	}

	public void setDuongDanAnh(String duongDanAnh) {
		this.duongDanAnh = duongDanAnh;
	}

	public void setThuTuHienThi(Integer thuTuHienThi) {
		this.thuTuHienThi = thuTuHienThi;
	}
}
