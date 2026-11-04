package com.phoneshop.dto;

/**
 * DTO chứa thông tin của một thông số kỹ thuật.
 */
public class ThongSoKyThuatDTO {

	private String tenThongSo;
	private String giaTri;
	private String donVi;

	public ThongSoKyThuatDTO() {
	}

	public ThongSoKyThuatDTO(String tenThongSo, String giaTri, String donVi) {
		this.tenThongSo = tenThongSo;
		this.giaTri = giaTri;
		this.donVi = donVi;
	}

	public String getTenThongSo() {
		return tenThongSo;
	}

	public void setTenThongSo(String tenThongSo) {
		this.tenThongSo = tenThongSo;
	}

	public String getGiaTri() {
		return giaTri;
	}

	public void setGiaTri(String giaTri) {
		this.giaTri = giaTri;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}
}