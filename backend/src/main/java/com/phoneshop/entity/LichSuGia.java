package com.phoneshop.entity;

//Import các annotation của JPA
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Đánh dấu đây là một Entity của JPA
@Entity

//Ánh xạ class LichSuGia với bảng LichSuGia trong database
@Table(name = "LichSuGia")
public class LichSuGia {
	// Khóa chính của bảng
	@Id

	// Giá trị được tự động tăng bởi database (AUTO_INCREMENT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Ánh xạ với cột MaLSG
	@Column(name = "MaLSG")
	private Integer maLSG;

	// Nhiều bản ghi lịch sử giá thuộc về một phiên bản sản phẩm
	@ManyToOne

	// MaPB là khóa ngoại tham chiếu đến PhienBan
	@JoinColumn(name = "MaPB", nullable = false)
	private PhienBan phienBan;

	// Giá của sản phẩm
	// precision = 15: tổng cộng tối đa 15 chữ số
	// scale = 2: có 2 chữ số sau dấu thập phân
	// Ví dụ: 15999999.00
	@Column(name = "Gia", precision = 15, scale = 2, nullable = false)
	private BigDecimal gia;

	// Thời điểm bắt đầu áp dụng mức giá này
	@Column(name = "NgayBatDau", nullable = false)
	private LocalDateTime ngayBatDau;

	// Thời điểm kết thúc áp dụng mức giá
	// Có thể NULL nếu đây là mức giá hiện tại
	@Column(name = "NgayKetThuc")
	private LocalDateTime ngayKetThuc;

	// Constructor không tham số
	// JPA cần constructor này để tạo đối tượng Entity
	public LichSuGia() {
	}

	// Getter và Setter cho maLSG
	public Integer getMaLSG() {
		return maLSG;
	}

	public void setMaLSG(Integer maLSG) {
		this.maLSG = maLSG;
	}

	// Getter và Setter cho phienBan
	public PhienBan getPhienBan() {
		return phienBan;
	}

	public void setPhienBan(PhienBan phienBan) {
		this.phienBan = phienBan;
	}

	// Getter và Setter cho gia
	public BigDecimal getGia() {
		return gia;
	}

	public void setGia(BigDecimal gia) {
		this.gia = gia;
	}

	// Getter và Setter cho ngayBatDau
	public LocalDateTime getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(LocalDateTime ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	// Getter và Setter cho ngayKetThuc
	public LocalDateTime getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
}
