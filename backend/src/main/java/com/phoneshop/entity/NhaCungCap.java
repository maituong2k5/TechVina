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

// Đánh dấu đây là một Entity của JPA
@Entity

// Ánh xạ class NhaCungCap với bảng NhaCungCap trong database
@Table(name = "NhaCungCap")
public class NhaCungCap {

	// Khóa chính của bảng
	@Id

	// Tự động tăng giá trị khóa chính
	// Tương ứng với AUTO_INCREMENT trong MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Ánh xạ với cột MaNCC
	@Column(name = "MaNCC")
	private Integer maNCC;

	// Tên nhà cung cấp
	// Tối đa 100 ký tự
	// Không được để trống
	// Không được trùng với nhà cung cấp khác
	@Column(name = "TenNCC", length = 100, nullable = false, unique = true)
	private String tenNCC;

	// Nhiều nhà cung cấp có thể cùng sử dụng một trạng thái
	// Ví dụ: nhiều nhà cung cấp có trạng thái "Đang hoạt động"
	@ManyToOne

	// MaTrangThai là khóa ngoại tham chiếu đến bảng TrangThaiNhaCungCap
	@JoinColumn(name = "MaTrangThai", nullable = false)
	private TrangThaiNhaCungCap trangThai;

	// Constructor không tham số
	// JPA cần constructor này để tạo đối tượng Entity
	public NhaCungCap() {
	}

	// Getter và Setter cho maNCC
	public Integer getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(Integer maNCC) {
		this.maNCC = maNCC;
	}

	// Getter và Setter cho tenNCC
	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	// Getter và Setter cho trangThai
	public TrangThaiNhaCungCap getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiNhaCungCap trangThai) {
		this.trangThai = trangThai;
	}
}
