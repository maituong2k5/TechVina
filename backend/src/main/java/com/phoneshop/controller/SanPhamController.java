package com.phoneshop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoneshop.entity.SanPham;
import com.phoneshop.service.SanPhamService;

/**
 * Controller cung cấp API liên quan đến sản phẩm.
 */
@RestController
@RequestMapping("/api/san-pham")
public class SanPhamController {

    // Service xử lý nghiệp vụ sản phẩm
    private final SanPhamService sanPhamService;

    // Constructor Injection
    public SanPhamController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    /**
     * API lấy danh sách sản phẩm nổi bật.
     *
     * GET /api/san-pham/noi-bat
     *
     * @return danh sách sản phẩm nổi bật
     */
    @GetMapping("/noi-bat")
    public List<SanPham> laySanPhamNoiBat() {
        return sanPhamService.laySanPhamNoiBat();
    }
}