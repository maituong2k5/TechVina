package com.phoneshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Đánh dấu đây là một Entity của JPA
@Entity

//Ánh xạ class Kho với bảng Kho trong database
@Table(name = "Kho")
public class Kho {

	// Khóa chính của bảng Kho
	@Id

	// Tự động tăng giá trị khóa chính
	// Tương ứng với AUTO_INCREMENT trong MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Ánh xạ với cột MaKho
	@Column(name = "MaKho")
	private Integer maKho;

	// Tên kho
	// length = 100: tối đa 100 ký tự
	// nullable = false: bắt buộc phải có giá trị
	// unique = true: không được trùng tên kho
	@Column(name = "TenKho", length = 100, nullable = false, unique = true)
	private String tenKho;

	// Địa chỉ của kho
	// Tối đa 255 ký tự và không được để trống
	@Column(name = "DiaChi", length = 255, nullable = false)
	private String diaChi;

	// Constructor không tham số
	// JPA cần constructor này để tạo đối tượng Entity
	public Kho() {
	}

	// Getter và Setter cho maKho
	public Integer getMaKho() {
		return maKho;
	}

	public void setMaKho(Integer maKho) {
		this.maKho = maKho;
	}

	// Getter và Setter cho tenKho
	public String getTenKho() {
		return tenKho;
	}

	public void setTenKho(String tenKho) {
		this.tenKho = tenKho;
	}

	// Getter và Setter cho diaChi
	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
}
