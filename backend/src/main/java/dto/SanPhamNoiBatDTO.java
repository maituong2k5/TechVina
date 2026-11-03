package dto;

import java.math.BigDecimal;

/**
 * DTO chứa thông tin sản phẩm nổi bật
 * dùng để trả dữ liệu cho Frontend.
 */
public class SanPhamNoiBatDTO {
	  // Thông tin sản phẩm
    private Integer maSP;
    private String tenSP;

    // Thông tin thương hiệu và danh mục
    private String tenHang;
    private String tenDanhMuc;

    // Ảnh đại diện của phiên bản
    private String anhDaiDien;

    // Giá bán hiện tại
    private BigDecimal giaBan;

    // Cấu hình dung lượng
    private String ram;
    private String rom;

    // Tổng số lượng tồn kho
    private Long soLuongTon;
    

    // Constructor không tham số
    public SanPhamNoiBatDTO() {
    }

    // Constructor đầy đủ tham số
    public SanPhamNoiBatDTO(
            Integer maSP,
            String tenSP,
            String tenHang,
            String tenDanhMuc,
            String anhDaiDien,
            BigDecimal giaBan,
            String ram,
            String rom,
            Long soLuongTon) {

        this.maSP = maSP;
        this.tenSP = tenSP;
        this.tenHang = tenHang;
        this.tenDanhMuc = tenDanhMuc;
        this.anhDaiDien = anhDaiDien;
        this.giaBan = giaBan;
        this.ram = ram;
        this.rom = rom;
        this.soLuongTon = soLuongTon;
    }

    // Getter và Setter
    public Integer getMaSP() {
        return maSP;
    }

    public void setMaSP(Integer maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public Long getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Long soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

}
