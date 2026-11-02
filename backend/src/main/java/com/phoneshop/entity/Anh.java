package com.phoneshop.entity;

//Import các annotation của JPA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// @Entity: Đánh dấu class Anh là một Entity của JPA
// JPA sẽ quản lý các đối tượng Anh và ánh xạ chúng với database
@Entity

// @Table: Xác định Entity này tương ứng với bảng "Anh" trong database
@Table(name = "Anh")
public class Anh {

	// @Id: Xác định maAnh là khóa chính (Primary Key)
	@Id

	// @GeneratedValue: Cho phép database tự động tạo giá trị cho khóa chính
	// GenerationType.IDENTITY: sử dụng AUTO_INCREMENT của MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// @Column: Ánh xạ thuộc tính maAnh với cột MaAnh trong database
	@Column(name = "MaAnh")
	private Integer maAnh;

	// @ManyToOne: Thiết lập quan hệ nhiều - một với Entity PhienBan
	// Một phiên bản có thể có nhiều hình ảnh
	@ManyToOne

	// @JoinColumn: Xác định cột khóa ngoại MaPB trong bảng Anh
	// MaPB tham chiếu đến MaPB của bảng PhienBan
	// nullable = false: Mỗi hình ảnh bắt buộc phải thuộc một phiên bản
	@JoinColumn(name = "MaPB", nullable = false)
	private PhienBan phienBan;

	// Ánh xạ thuộc tính duongDanAnh với cột DuongDanAnh trong database
	// length = 200: Đường dẫn ảnh tối đa 200 ký tự
	// nullable = false: Đường dẫn ảnh bắt buộc phải có
	@Column(name = "DuongDanAnh", length = 200, nullable = false)
	private String duongDanAnh;

	// Ánh xạ thuộc tính thuTuHienThi với cột ThuTuHienThi trong database
	// nullable = false: Thứ tự hiển thị bắt buộc phải có
	@Column(name = "ThuTuHienThi", nullable = false)
	private Integer thuTuHienThi;

	// Constructor không tham số
	// JPA yêu cầu Entity phải có constructor không tham số
	public Anh() {
	}

	// Getter: Lấy mã hình ảnh
	public Integer getMaAnh() {
		return maAnh;
	}

	// Setter: Gán mã hình ảnh
	public void setMaAnh(Integer maAnh) {
		this.maAnh = maAnh;
	}

	// Getter: Lấy phiên bản mà hình ảnh thuộc về
	public PhienBan getPhienBan() {
		return phienBan;
	}

	// Setter: Gán phiên bản cho hình ảnh
	public void setPhienBan(PhienBan phienBan) {
		this.phienBan = phienBan;
	}

	// Getter: Lấy đường dẫn hình ảnh
	public String getDuongDanAnh() {
		return duongDanAnh;
	}

	// Setter: Gán đường dẫn hình ảnh
	public void setDuongDanAnh(String duongDanAnh) {
		this.duongDanAnh = duongDanAnh;
	}

	// Getter: Lấy thứ tự hiển thị của hình ảnh
	public Integer getThuTuHienThi() {
		return thuTuHienThi;
	}

	// Setter: Gán thứ tự hiển thị cho hình ảnh
	public void setThuTuHienThi(Integer thuTuHienThi) {
		this.thuTuHienThi = thuTuHienThi;
	}
}
