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
@Table(name = "TonKho")
public class TonKho {

	// Khóa chính của bảng TonKho
	@Id

	// Tự động tăng giá trị khóa chính
	// Tương ứng với AUTO_INCREMENT trong MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Ánh xạ với cột MaTonKho
	@Column(name = "MaTonKho")
	private Integer maTonKho;

	// Nhiều bản ghi tồn kho có thể thuộc về một kho
	@ManyToOne

	// MaKho là khóa ngoại tham chiếu đến bảng Kho
	@JoinColumn(name = "MaKho", nullable = false)
	private Kho kho;

	// Nhiều bản ghi tồn kho có thể thuộc về một phiên bản sản phẩm
	@ManyToOne

	// MaPB là khóa ngoại tham chiếu đến bảng PhienBan
	@JoinColumn(name = "MaPB", nullable = false)
	private PhienBan phienBan;

	// Số lượng sản phẩm hiện đang tồn trong kho
	// Không được để trống
	@Column(name = "SoLuongTon", nullable = false)
	private Integer soLuongTon;

	// Constructor không tham số
	// JPA cần constructor này để tạo đối tượng Entity
	public TonKho() {
	}

	// Getter và Setter cho maTonKho
	public Integer getMaTonKho() {
		return maTonKho;
	}

	public void setMaTonKho(Integer maTonKho) {
		this.maTonKho = maTonKho;
	}

	// Getter và Setter cho kho
	public Kho getKho() {
		return kho;
	}

	public void setKho(Kho kho) {
		this.kho = kho;
	}

	// Getter và Setter cho phienBan
	public PhienBan getPhienBan() {
		return phienBan;
	}

	public void setPhienBan(PhienBan phienBan) {
		this.phienBan = phienBan;
	}

	// Getter và Setter cho soLuongTon
	public Integer getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(Integer soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
}
