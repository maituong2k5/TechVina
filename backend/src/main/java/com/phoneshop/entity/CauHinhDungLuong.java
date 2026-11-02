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

// @Entity: Đánh dấu class CauHinhDungLuong là một Entity của JPA
// JPA sẽ quản lý các đối tượng CauHinhDungLuong và ánh xạ chúng với database
@Entity

// @Table: Xác định Entity này tương ứng với bảng "CauHinhDungLuong"
@Table(name = "CauHinhDungLuong")
public class CauHinhDungLuong {

	// @Id: Xác định maCHDL là khóa chính của bảng
	@Id

	// @GeneratedValue: Cho phép database tự động tạo giá trị cho khóa chính
	// GenerationType.IDENTITY: sử dụng AUTO_INCREMENT của MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// @Column: Ánh xạ thuộc tính maCHDL với cột MaCHDL trong database
	@Column(name = "MaCHDL")
	private Integer maCHDL;

	// @ManyToOne: Thiết lập quan hệ nhiều - một với Entity Ram
	// Nhiều cấu hình dung lượng có thể sử dụng cùng một mức RAM
	// Ví dụ: nhiều cấu hình có thể cùng sử dụng RAM 8GB
	@ManyToOne

	// @JoinColumn: Xác định cột khóa ngoại trong bảng CauHinhDungLuong
	// MaRam là khóa ngoại tham chiếu đến MaRam của bảng Ram
	// nullable = false: Bắt buộc mỗi cấu hình phải có RAM
	@JoinColumn(name = "MaRam", nullable = false)
	private Ram ram;

	// @ManyToOne: Thiết lập quan hệ nhiều - một với Entity Rom
	// Nhiều cấu hình dung lượng có thể sử dụng cùng một mức ROM
	// Ví dụ: nhiều cấu hình có thể cùng sử dụng ROM 256GB
	@ManyToOne

	// @JoinColumn: Xác định cột khóa ngoại trong bảng CauHinhDungLuong
	// MaRom là khóa ngoại tham chiếu đến MaRom của bảng Rom
	// nullable = false: Bắt buộc mỗi cấu hình phải có ROM

	@JoinColumn(name = "MaRom", nullable = false)
	private Rom rom;

	// Constructor không tham số
	// JPA yêu cầu Entity phải có constructor không tham số
	public CauHinhDungLuong() {
	}

	// Getter: Lấy mã cấu hình dung lượng
	public Integer getMaCHDL() {
		return maCHDL;
	}

	// Setter: Gán mã cấu hình dung lượng
	public void setMaCHDL(Integer maCHDL) {
		this.maCHDL = maCHDL;
	}

	// Getter: Lấy đối tượng Ram của cấu hình
	public Ram getRam() {
		return ram;
	}

	// Setter: Gán đối tượng Ram cho cấu hình
	public void setRam(Ram ram) {
		this.ram = ram;
	}

	// Getter: Lấy đối tượng Rom của cấu hình
	public Rom getRom() {
		return rom;
	}

	// Setter: Gán đối tượng Rom cho cấu hình
	public void setRom(Rom rom) {
		this.rom = rom;
	}
}
