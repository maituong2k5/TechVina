package com.phoneshop.entity;

//Import các annotation của JPA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Entity: Đánh dấu class TrangThaiNhaCungCap là một Entity của JPA
// JPA sẽ quản lý các đối tượng TrangThaiNhaCungCap và ánh xạ chúng với database
@Entity

//@Table: Xác định Entity này tương ứng với bảng "TrangThaiNhaCungCap"
@Table(name = "TrangThaiNhaCungCap")
public class TrangThaiNhaCungCap {

	// @Id: Xác định maTrangThai là khóa chính (Primary Key)
	@Id

	// @GeneratedValue: Cho phép database tự động tạo giá trị cho khóa chính
	// GenerationType.IDENTITY: sử dụng AUTO_INCREMENT của MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// @Column: Ánh xạ thuộc tính maTrangThai với cột MaTrangThai trong database
	@Column(name = "MaTrangThai")
	private Integer maTrangThai;

	// Ánh xạ thuộc tính tenTrangThai với cột TenTrangThai trong database
	// length = 50: Độ dài tối đa của tên trạng thái là 50 ký tự
	// nullable = false: Tên trạng thái bắt buộc phải có
	// unique = true: Không cho phép hai trạng thái có cùng tên
	@Column(name = "TenTrangThai", length = 50, nullable = false, unique = true)
	private String tenTrangThai;

	// Constructor không tham số
	// JPA yêu cầu Entity phải có constructor không tham số
	public TrangThaiNhaCungCap() {
	}

	// Getter và Setter cho maTrangThai
	public Integer getMaTrangThai() {
		return maTrangThai;
	}

	public void setMaTrangThai(Integer maTrangThai) {
		this.maTrangThai = maTrangThai;
	}

	// Getter và Setter cho tenTrangThai
	public String getTenTrangThai() {
		return tenTrangThai;
	}

	public void setTenTrangThai(String tenTrangThai) {
		this.tenTrangThai = tenTrangThai;
	}
}
