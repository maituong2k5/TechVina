package com.phoneshop.entity;

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
 * Entity ánh xạ với bảng LichSuTrangThaiNhaCungCap. Dùng để lưu lịch sử thay
 * đổi trạng thái của nhà cung cấp.
 */
@Entity
@Table(name = "LichSuTrangThaiNhaCungCap")
public class LichSuTrangThaiNhaCungCap {
	// Khóa chính của lịch sử thay đổi trạng thái
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaLichSu")
	private Integer maLichSu;

	// Nhà cung cấp được thay đổi trạng thái
	@ManyToOne
	@JoinColumn(name = "MaNCC", nullable = false)
	private NhaCungCap nhaCungCap;

	// Trạng thái của nhà cung cấp trước khi thay đổi
	@ManyToOne
	@JoinColumn(name = "MaTrangThaiCu", nullable = false)
	private TrangThaiNhaCungCap trangThaiCu;

	// Trạng thái mới sau khi thay đổi
	@ManyToOne
	@JoinColumn(name = "MaTrangThaiMoi", nullable = false)
	private TrangThaiNhaCungCap trangThaiMoi;

	// Thời điểm thực hiện thay đổi
	@Column(name = "ThoiDiem", nullable = false)
	private LocalDateTime thoiDiem;

	// Nhân viên thực hiện thay đổi trạng thái
	@ManyToOne
	@JoinColumn(name = "MaNhanVien", nullable = false)
	private NhanVien nhanVien;

	// Constructor rỗng để JPA/Hibernate khởi tạo Entity
	public LichSuTrangThaiNhaCungCap() {
	}

	// Getter và Setter
	public Integer getMaLichSu() {
		return maLichSu;
	}

	public void setMaLichSu(Integer maLichSu) {
		this.maLichSu = maLichSu;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public TrangThaiNhaCungCap getTrangThaiCu() {
		return trangThaiCu;
	}

	public void setTrangThaiCu(TrangThaiNhaCungCap trangThaiCu) {
		this.trangThaiCu = trangThaiCu;
	}

	public TrangThaiNhaCungCap getTrangThaiMoi() {
		return trangThaiMoi;
	}

	public void setTrangThaiMoi(TrangThaiNhaCungCap trangThaiMoi) {
		this.trangThaiMoi = trangThaiMoi;
	}

	public LocalDateTime getThoiDiem() {
		return thoiDiem;
	}

	public void setThoiDiem(LocalDateTime thoiDiem) {
		this.thoiDiem = thoiDiem;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
}
