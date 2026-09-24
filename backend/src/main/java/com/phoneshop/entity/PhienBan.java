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

// @Entity: Đánh dấu class PhienBan là một Entity của JPA
// JPA sẽ quản lý các đối tượng PhienBan và ánh xạ chúng với database
@Entity

// @Table: Xác định Entity này tương ứng với bảng "PhienBan" trong database
@Table(name = "PhienBan")
public class PhienBan {

	// @Id: Xác định maPB là khóa chính (Primary Key)
	@Id

	// @GeneratedValue: Cho phép database tự động tạo giá trị cho khóa chính
	// GenerationType.IDENTITY: sử dụng AUTO_INCREMENT của MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// @Column: Ánh xạ thuộc tính maPB với cột MaPB trong database
	@Column(name = "MaPB")
	private Integer maPB;

	// Ánh xạ thuộc tính sku với cột SKU trong database
	// length = 50: Độ dài tối đa của SKU là 50 ký tự
	// nullable = false: SKU bắt buộc phải có
	// unique = true: Không cho phép hai phiên bản có cùng SKU
	@Column(name = "SKU", length = 50, nullable = false, unique = true)
	private String sku;

	// @ManyToOne: Thiết lập quan hệ nhiều - một với Entity SanPham
	// Một sản phẩm có thể có nhiều phiên bản
	@ManyToOne

	// @JoinColumn: Xác định cột khóa ngoại MaSP trong bảng PhienBan
	// MaSP tham chiếu đến MaSP của bảng SanPham
	// nullable = false: Phiên bản bắt buộc phải thuộc một sản phẩm
	@JoinColumn(name = "MaSP", nullable = false)
	private SanPham sanPham;

	// @ManyToOne: Thiết lập quan hệ nhiều - một với Entity CauHinhDungLuong
	// Một cấu hình dung lượng có thể được sử dụng cho nhiều phiên bản
	@ManyToOne

	// @JoinColumn: Xác định cột khóa ngoại MaCHDL trong bảng PhienBan
	// MaCHDL tham chiếu đến MaCHDL của bảng CauHinhDungLuong
	// nullable = false: Phiên bản bắt buộc phải có cấu hình dung lượng
	@JoinColumn(name = "MaCHDL", nullable = false)
	private CauHinhDungLuong cauHinhDungLuong;

	// @ManyToOne: Thiết lập quan hệ nhiều - một với Entity MauSac
	// Một màu sắc có thể được sử dụng cho nhiều phiên bản
	@ManyToOne

	// @JoinColumn: Xác định cột khóa ngoại MaMauSac trong bảng PhienBan
	// MaMauSac tham chiếu đến MaMauSac của bảng MauSac
	// nullable = false: Phiên bản bắt buộc phải có màu sắc
	@JoinColumn(name = "MaMauSac", nullable = false)
	private MauSac mauSac;

	// @ManyToOne: Thiết lập quan hệ nhiều - một với Entity ThiTruong
	// Một thị trường có thể có nhiều phiên bản sản phẩm
	@ManyToOne

	// @JoinColumn: Xác định cột khóa ngoại MaThiTruong trong bảng PhienBan
	// MaThiTruong tham chiếu đến MaThiTruong của bảng ThiTruong
	// nullable = false: Phiên bản bắt buộc phải thuộc một thị trường
	@JoinColumn(name = "MaThiTruong", nullable = false)
	private ThiTruong thiTruong;

	// Constructor không tham số
	// JPA yêu cầu Entity phải có constructor không tham số
	public PhienBan() {
	}

	// Getter: Lấy mã phiên bản
	public Integer getMaPB() {
		return maPB;
	}

	// Setter: Gán mã phiên bản
	public void setMaPB(Integer maPB) {
		this.maPB = maPB;
	}

	// Getter: Lấy SKU của phiên bản
	public String getSku() {
		return sku;
	}

	// Setter: Gán SKU cho phiên bản
	public void setSku(String sku) {
		this.sku = sku;
	}

	// Getter: Lấy sản phẩm của phiên bản
	public SanPham getSanPham() {
		return sanPham;
	}

	// Setter: Gán sản phẩm cho phiên bản
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	// Getter: Lấy cấu hình dung lượng của phiên bản
	public CauHinhDungLuong getCauHinhDungLuong() {
		return cauHinhDungLuong;
	}

	// Setter: Gán cấu hình dung lượng cho phiên bản
	public void setCauHinhDungLuong(CauHinhDungLuong cauHinhDungLuong) {
		this.cauHinhDungLuong = cauHinhDungLuong;
	}

	// Getter: Lấy màu sắc của phiên bản
	public MauSac getMauSac() {
		return mauSac;
	}

	// Setter: Gán màu sắc cho phiên bản
	public void setMauSac(MauSac mauSac) {
		this.mauSac = mauSac;
	}

	// Getter: Lấy thị trường của phiên bản
	public ThiTruong getThiTruong() {
		return thiTruong;
	}

	// Setter: Gán thị trường cho phiên bản
	public void setThiTruong(ThiTruong thiTruong) {
		this.thiTruong = thiTruong;
	}
}
