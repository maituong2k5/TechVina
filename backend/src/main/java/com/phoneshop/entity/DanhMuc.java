package com.phoneshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity ánh xạ với bảng DanhMuc trong CSDL.
 */
@Entity
@Table(name = "danhmuc")
public class DanhMuc {

    // Khóa chính
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDanhMuc")
    private Integer maDanhMuc;

    // Tên danh mục
    @Column(name = "TenDanhMuc", length = 100, nullable = false, unique = true)
    private String tenDanhMuc;

    // Đường dẫn hình ảnh danh mục
    @Column(name = "DuongDanAnh", length = 255)
    private String duongDanAnh;

    // Thứ tự hiển thị trên website
    @Column(name = "ThuTuHienThi", nullable = false)
    private Integer thuTuHienThi = 0;

    // Trạng thái: true = hiển thị, false = ẩn
    @Column(name = "TrangThai", nullable = false)
    private Boolean trangThai = true;
    
    // Constructor rỗng để JPA sử dụng
    public DanhMuc() {
    }

    // Getter và Setter
    public Integer getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(Integer maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getDuongDanAnh() {
        return duongDanAnh;
    }

    public void setDuongDanAnh(String duongDanAnh) {
        this.duongDanAnh = duongDanAnh;
    }

    public Integer getThuTuHienThi() {
        return thuTuHienThi;
    }

    public void setThuTuHienThi(Integer thuTuHienThi) {
        this.thuTuHienThi = thuTuHienThi;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }
       
}