
package com.phoneshop.dto;

import java.util.List;

/**
 * DTO chứa thông tin một nhóm thông số kỹ thuật và danh sách thông số thuộc
 * nhóm đó.
 */
public class NhomThongSoDTO {

	private String tenNhom;
	private List<ThongSoKyThuatDTO> danhSachThongSo;

	public NhomThongSoDTO() {
	}

	public NhomThongSoDTO(String tenNhom, List<ThongSoKyThuatDTO> danhSachThongSo) {
		this.tenNhom = tenNhom;
		this.danhSachThongSo = danhSachThongSo;
	}

	public String getTenNhom() {
		return tenNhom;
	}

	public void setTenNhom(String tenNhom) {
		this.tenNhom = tenNhom;
	}

	public List<ThongSoKyThuatDTO> getDanhSachThongSo() {
		return danhSachThongSo;
	}

	public void setDanhSachThongSo(List<ThongSoKyThuatDTO> danhSachThongSo) {
		this.danhSachThongSo = danhSachThongSo;
	}
}
