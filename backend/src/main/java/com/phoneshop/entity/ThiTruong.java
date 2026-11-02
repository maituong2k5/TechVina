package com.phoneshop.entity;

//Import các annotation của JPA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Entity: Đánh dấu class ThiTruong là một Entity của JPA
// JPA sẽ quản lý các đối tượng ThiTruong và ánh xạ chúng với database
@Entity

// @Table: Xác định Entity này tương ứng với bảng "ThiTruong" trong database	
@Table(name = "ThiTruong")
public class ThiTruong {

	// @Id: Xác định maThiTruong là khóa chính (Primary Key)
	@Id

	// @GeneratedValue: Cho phép database tự động tạo giá trị cho khóa chính
	// GenerationType.IDENTITY: sử dụng cơ chế AUTO_INCREMENT của MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// @Column: Ánh xạ thuộc tính maThiTruong với cột MaThiTruong trong database
	@Column(name = "MaThiTruong")
	private Integer maThiTruong;

	// Ánh xạ maCode với cột MaCode trong database
	// nullable = false: Không cho phép giá trị NULL
	// unique = true: Không cho phép hai thị trường có cùng mã code
	// length = 50: Độ dài tối đa của chuỗi là 50 ký tự
	@Column(name = "MaCode", nullable = false, unique = true, length = 50)
	private String maCode;

	// Ánh xạ tenThiTruong với cột TenThiTruong trong database
	// nullable = false: Bắt buộc phải có tên thị trường
	// unique = true: Không cho phép hai thị trường có cùng tên
	// length = 20: Độ dài tối đa của chuỗi là 20 ký tự
	@Column(name = "TenThiTruong", nullable = false, unique = true, length = 20)
	private String tenThiTruong;

	// Constructor không tham số
	// JPA yêu cầu Entity phải có constructor không tham số
	public ThiTruong() {
	}

	// Getter: Lấy giá trị của maThiTruong
	public Integer getMaThiTruong() {
		return maThiTruong;
	}

	// Setter: Gán giá trị cho maThiTruong
	public void setMaThiTruong(Integer maThiTruong) {
		this.maThiTruong = maThiTruong;
	}

	// Getter: Lấy giá trị của maCode
	public String getMaCode() {
		return maCode;
	}

	// Setter: Gán giá trị cho maCode
	public void setMaCode(String maCode) {
		this.maCode = maCode;
	}

	// Getter: Lấy giá trị của tenThiTruong
	public String getTenThiTruong() {
		return tenThiTruong;
	}

	// Setter: Gán giá trị cho tenThiTruong
	public void setTenThiTruong(String tenThiTruong) {
		this.tenThiTruong = tenThiTruong;
	}
}
