package com.phoneshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.phoneshop.entity.SanPham;
import com.phoneshop.repository.SanPhamRepository;

/**
 * Service xử lý nghiệp vụ liên quan đến sản phẩm.
 */
@Service
public class SanPhamService {

    // Repository dùng để truy vấn dữ liệu sản phẩm
    private final SanPhamRepository sanPhamRepository;

    // Constructor Injection: Spring tự động cung cấp Repository
    public SanPhamService(SanPhamRepository sanPhamRepository) {
        this.sanPhamRepository = sanPhamRepository;
    }

    /**
     * Lấy danh sách sản phẩm nổi bật đang hoạt động.
     */
    public List<SanPham> laySanPhamNoiBat() {
        return sanPhamRepository.findByTrangThaiTrueAndNoiBatTrue();
    }
}