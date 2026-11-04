package com.phoneshop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoneshop.dto.SanPhamNoiBatDTO;
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
	 * API lấy danh sách sản phẩm nổi bật kèm ảnh, giá bán và cấu hình đại diện.
	 *
	 * GET /api/san-pham/noi-bat
	 *
	 * @return Danh sách sản phẩm nổi bật dạng DTO
	 */
	@GetMapping("/noi-bat")
	public List<SanPhamNoiBatDTO> laySanPhamNoiBat() {
		return sanPhamService.layDanhSachSanPhamNoiBat();
	}
}