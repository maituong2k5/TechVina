
package com.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoneshop.dto.SanPhamChiTietDTO;
import com.phoneshop.service.SanPhamChiTietService;

/**
 * Controller cung cấp API thông tin cơ bản của sản phẩm.
 */
@RestController
@RequestMapping("/api/san-pham")
public class SanPhamChiTietController {

	private final SanPhamChiTietService sanPhamChiTietService;

	// Constructor Injection Service.
	public SanPhamChiTietController(SanPhamChiTietService sanPhamChiTietService) {
		this.sanPhamChiTietService = sanPhamChiTietService;
	}

	/**
	 * API lấy thông tin cơ bản của sản phẩm theo mã.
	 */
	@GetMapping("/{maSP}/chi-tiet")
	public ResponseEntity<SanPhamChiTietDTO> layThongTinSanPham(@PathVariable Integer maSP) {

		SanPhamChiTietDTO ketQua = sanPhamChiTietService.layThongTinSanPham(maSP);

		return ResponseEntity.ok(ketQua);
	}
}
