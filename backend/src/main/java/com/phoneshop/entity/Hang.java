package com.phoneshop.entity;

//Import các annotation của JPA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Entity: Đánh dấu class Hang là một Entity của JPA
// JPA sẽ quản lý các đối tượng Hang và ánh xạ chúng với database
@Entity

// @Table: Xác định Entity Hang tương ứng với bảng "Hang" trong database
@Table(name = "Hang")
public class Hang {

	// @Id: Xác định maHang là khóa chính (Primary Key)
	@Id

	// @GeneratedValue: Cho phép database tự động tạo giá trị cho khóa chính
	// GenerationType.IDENTITY: sử dụng AUTO_INCREMENT của MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// @Column: Ánh xạ thuộc tính maHang với cột MaHang trong database
	@Column(name = "MaHang")
	private Integer maHang;

	// Ánh xạ thuộc tính tenHang với cột TenHang trong database
	// length = 50: Độ dài tối đa của tên hãng là 50 ký tự
	// nullable = false: Tên hãng bắt buộc phải có
	// unique = true: Không cho phép hai hãng có cùng tên
	@Column(name = "TenHang", length = 50, nullable = false, unique = true)
	private String tenHang;

	// Constructor không tham số
	// JPA yêu cầu Entity phải có constructor không tham số
	public Hang() {
	}

	// Getter: Lấy mã hãng
	public Integer getMaHang() {
		return maHang;
	}

	// Setter: Gán mã hãng
	public void setMaHang(Integer maHang) {
		this.maHang = maHang;
	}

	// Getter: Lấy tên hãng
	public String getTenHang() {
		return tenHang;
	}

	// Setter: Gán tên hãng
	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}
}
