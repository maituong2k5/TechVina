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
 * Entity ánh xạ với bảng PhieuNhap. Lưu thông tin các phiếu nhập hàng từ nhà
 * cung cấp vào kho.
 */
@Entity
@Table(name = "PhieuNhap")
public class PhieuNhap {
	// Khóa chính của phiếu nhập
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaPN")
	private Integer maPN;

	// Nhà cung cấp thực hiện việc cung cấp hàng
	@ManyToOne
	@JoinColumn(name = "MaNCC", nullable = false)
	private NhaCungCap nhaCungCap;

	// Kho nhận hàng
	@ManyToOne
	@JoinColumn(name = "MaKho", nullable = false)
	private Kho kho;

	// Thời điểm tạo phiếu nhập
	@Column(name = "NgayTao", nullable = false)
	private LocalDateTime ngayTao;

	// Thời điểm hàng thực tế được nhập vào kho
	@Column(name = "NgayNhap")
	private LocalDateTime ngayNhap;

	// Trạng thái hiện tại của phiếu nhập
	@ManyToOne
	@JoinColumn(name = "MaTrangThai", nullable = false)
	private TrangThaiPhieuNhap trangThai;

	// Constructor rỗng để JPA/Hibernate khởi tạo Entity
	public PhieuNhap() {
	}

	// Getter và Setter
	public Integer getMaPN() {
		return maPN;
	}

	public void setMaPN(Integer maPN) {
		this.maPN = maPN;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public Kho getKho() {
		return kho;
	}

	public void setKho(Kho kho) {
		this.kho = kho;
	}

	public LocalDateTime getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDateTime ngayTao) {
		this.ngayTao = ngayTao;
	}

	public LocalDateTime getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(LocalDateTime ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public TrangThaiPhieuNhap getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiPhieuNhap trangThai) {
		this.trangThai = trangThai;
	}
}
