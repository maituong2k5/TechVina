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

// @Entity: Đánh dấu class SanPham là một Entity của JPA
// JPA sẽ quản lý các đối tượng SanPham và ánh xạ chúng với database
@Entity

// @Table: Xác định Entity này tương ứng với bảng "SanPham" trong database
@Table(name = "SanPham")

public class SanPham {

	// @Id: Xác định maSP là khóa chính (Primary Key)
	@Id

	// @GeneratedValue: Cho phép database tự động tạo giá trị cho khóa chính
	// GenerationType.IDENTITY: sử dụng AUTO_INCREMENT của MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// @Column: Ánh xạ thuộc tính maSP với cột MaSP trong database
	@Column(name = "MaSP")
	private Integer maSP;

	// Ánh xạ thuộc tính tenSP với cột TenSP trong database
	// length = 50: Độ dài tối đa của tên sản phẩm là 50 ký tự
	// nullable = false: Tên sản phẩm bắt buộc phải có
	// unique = true: Không cho phép hai sản phẩm có cùng tên
	@Column(name = "TenSP", length = 50, nullable = false, unique = true)
	private String tenSP;

	// Ánh xạ thuộc tính maModel với cột MaModel trong database
	// length = 30: Độ dài tối đa của mã model là 30 ký tự
	// nullable = false: Bắt buộc phải có mã model
	// unique = true: Không cho phép hai sản phẩm có cùng mã model
	@Column(name = "MaModel", length = 30, nullable = false, unique = true)
	private String maModel;

	// @ManyToOne: Thiết lập quan hệ nhiều - một với Entity DanhMuc
	// Nhiều sản phẩm có thể thuộc cùng một danh mục
	@ManyToOne

	// @JoinColumn: Xác định cột khóa ngoại trong bảng SanPham
	// MaDanhMuc là khóa ngoại tham chiếu đến MaDanhMuc của bảng DanhMuc
	// nullable = false: Sản phẩm bắt buộc phải có danh mục
	@JoinColumn(name = "MaDanhMuc", nullable = false)
	private DanhMuc danhMuc;

	// @ManyToOne: Thiết lập quan hệ nhiều - một với Entity Hang
	// Nhiều sản phẩm có thể thuộc cùng một hãng
	@ManyToOne

	// @JoinColumn: Xác định cột khóa ngoại trong bảng SanPham
	// MaHang là khóa ngoại tham chiếu đến MaHang của bảng Hang
	// nullable = false: Sản phẩm bắt buộc phải có hãng
	@JoinColumn(name = "MaHang", nullable = false)
	private Hang hang;

	// Ánh xạ thuộc tính trangThai với cột TrangThai trong database
	// nullable = false: Trạng thái không được phép NULL
	// true: Giá trị mặc định của trạng thái là đang hoạt động
	@Column(name = "TrangThai", nullable = false)
	private Boolean trangThai = true;

	// Constructor không tham số
	// JPA yêu cầu Entity phải có constructor không tham số
	public SanPham() {
	}

	// Getter: Lấy mã sản phẩm
	public Integer getMaSP() {
		return maSP;
	}

	// Setter: Gán mã sản phẩm
	public void setMaSP(Integer maSP) {
		this.maSP = maSP;
	}

	// Getter: Lấy tên sản phẩm
	public String getTenSP() {
		return tenSP;
	}

	// Setter: Gán tên sản phẩm
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	// Getter: Lấy mã model của sản phẩm
	public String getMaModel() {
		return maModel;
	}

	// Setter: Gán mã model cho sản phẩm
	public void setMaModel(String maModel) {
		this.maModel = maModel;
	}

	// Getter: Lấy danh mục của sản phẩm
	public DanhMuc getDanhMuc() {
		return danhMuc;
	}

	// Setter: Gán danh mục cho sản phẩm
	public void setDanhMuc(DanhMuc danhMuc) {
		this.danhMuc = danhMuc;
	}

	// Getter: Lấy hãng của sản phẩm
	public Hang getHang() {
		return hang;
	}

	// Setter: Gán hãng cho sản phẩm
	public void setHang(Hang hang) {
		this.hang = hang;
	}

	// Getter: Lấy trạng thái của sản phẩm
	public Boolean getTrangThai() {
		return trangThai;
	}

	// Setter: Thay đổi trạng thái của sản phẩm
	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}
}
