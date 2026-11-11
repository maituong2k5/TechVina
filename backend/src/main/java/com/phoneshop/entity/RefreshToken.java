package com.phoneshop.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity đại diện cho bảng RefreshToken trong Database.
 *
 * Lưu thông tin Refresh Token của khách hàng, phục vụ xác thực và duy trì phiên
 * đăng nhập.
 */
@Entity
@Table(name = "refreshtoken")
public class RefreshToken {
	// Khóa chính của RefreshToken.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaRefreshToken")
	private Integer maRefreshToken;

	// Khách hàng sở hữu Refresh Token.
	// Một khách hàng có thể có nhiều Refresh Token.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaKH", nullable = false)
	private KhachHang khachHang;

	// Nhân viên sở hữu Refresh Token.
	// Một nhân viên có thể có nhiều Refresh Token.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaNhanVien")
	private NhanVien nhanVien;

	// Lưu giá trị Hash của Refresh Token.
	// Không lưu Refresh Token gốc trong Database.
	@Column(name = "TokenHash", nullable = false, unique = true, length = 255)
	private String tokenHash;

	// Thời điểm Refresh Token được tạo.
	@Column(name = "NgayTao", nullable = false)
	private LocalDateTime ngayTao;

	// Thời điểm Refresh Token hết hạn.
	@Column(name = "NgayHetHan", nullable = false)
	private LocalDateTime ngayHetHan;

	// Thời điểm Refresh Token bị thu hồi.
	// NULL nghĩa là Token chưa bị thu hồi.
	@Column(name = "NgayThuHoi")
	private LocalDateTime ngayThuHoi;

	/**
	 * Constructor mặc định.
	 *
	 * JPA yêu cầu Entity phải có constructor không tham số với phạm vi truy cập
	 * public hoặc protected.
	 */
	public RefreshToken() {
	}

	// Getter và Setter cho MaRefreshToken.
	public Integer getMaRefreshToken() {
		return maRefreshToken;
	}

	public void setMaRefreshToken(Integer maRefreshToken) {
		this.maRefreshToken = maRefreshToken;
	}

	// Getter và Setter cho KhachHang.
	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	// Getter và Setter cho NhanVien.
	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	// Getter và Setter cho TokenHash.
	public String getTokenHash() {
		return tokenHash;
	}

	public void setTokenHash(String tokenHash) {
		this.tokenHash = tokenHash;
	}

	// Getter và Setter cho NgayTao.
	public LocalDateTime getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDateTime ngayTao) {
		this.ngayTao = ngayTao;
	}

	// Getter và Setter cho NgayHetHan.
	public LocalDateTime getNgayHetHan() {
		return ngayHetHan;
	}

	public void setNgayHetHan(LocalDateTime ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}

	// Getter và Setter cho NgayThuHoi.
	public LocalDateTime getNgayThuHoi() {
		return ngayThuHoi;
	}

	public void setNgayThuHoi(LocalDateTime ngayThuHoi) {
		this.ngayThuHoi = ngayThuHoi;
	}
}