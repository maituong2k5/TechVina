package com.phoneshop.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "danhmuc")
public class DanhMuc {

	// Khóa chính
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaDanhMuc")
	private Integer maDanhMuc;

	// Tên danh mục
	@Column(name = "TenDanhMuc", length = 100, nullable = false, unique = true)
	private String tenDanhMuc;

	// Danh mục cha
	@ManyToOne
	@JoinColumn(name = "MaDanhMucCha")
	@JsonBackReference
	private DanhMuc danhMucCha;

	// Danh sách danh mục con
	@OneToMany(mappedBy = "danhMucCha")
	@JsonManagedReference
	private List<DanhMuc> danhMucCon;

	// Đường dẫn hình ảnh của danh mục
	@Column(name = "DuongDanAnh", length = 255)
	private String duongDanAnh;

	// Thứ tự hiển thị danh mục
	@Column(name = "ThuTuHienThi", nullable = false)
	private Integer thuTuHienThi;

	// Trạng thái hoạt động của danh mục
	@Column(name = "TrangThai", nullable = false)
	private Boolean trangThai;

	// Constructor rỗng để JPA sử dụng
	public DanhMuc() {
	}

	// Getter và Setter
	public Integer getMaDanhMuc() {
		return maDanhMuc;
	}

	public void setMaDanhMuc(Integer maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}

	public DanhMuc getDanhMucCha() {
		return danhMucCha;
	}

	public void setDanhMucCha(DanhMuc danhMucCha) {
		this.danhMucCha = danhMucCha;
	}

	public List<DanhMuc> getDanhMucCon() {
		return danhMucCon;
	}

	public void setDanhMucCon(List<DanhMuc> danhMucCon) {
		this.danhMucCon = danhMucCon;
	}

	public String getDuongDanAnh() {
		return duongDanAnh;
	}

	public void setDuongDanAnh(String duongDanAnh) {
		this.duongDanAnh = duongDanAnh;
	}

	public Integer getThuTuHienThi() {
		return thuTuHienThi;
	}

	public void setThuTuHienThi(Integer thuTuHienThi) {
		this.thuTuHienThi = thuTuHienThi;
	}

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}
}