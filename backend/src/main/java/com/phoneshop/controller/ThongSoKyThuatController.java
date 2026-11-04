
package com.phoneshop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoneshop.dto.NhomThongSoDTO;
import com.phoneshop.service.ThongSoKyThuatService;

/**
 * Controller cung cấp API thông số kỹ thuật sản phẩm.
 */
@RestController
@RequestMapping("/api/thong-so-ky-thuat")
public class ThongSoKyThuatController {

	// Service xử lý nghiệp vụ thông số kỹ thuật
	private final ThongSoKyThuatService thongSoKyThuatService;

	/**
	 * Constructor Injection.
	 */
	public ThongSoKyThuatController(ThongSoKyThuatService thongSoKyThuatService) {
		this.thongSoKyThuatService = thongSoKyThuatService;
	}

	/**
	 * API lấy thông số kỹ thuật theo mã sản phẩm.
	 *
	 * Ví dụ: GET /api/thong-so-ky-thuat/21
	 */
	@GetMapping("/{maSP}")
	public ResponseEntity<List<NhomThongSoDTO>> layThongSoTheoSanPham(@PathVariable Integer maSP) {

		List<NhomThongSoDTO> ketQua = thongSoKyThuatService.layThongSoTheoSanPham(maSP);

		return ResponseEntity.ok(ketQua);
	}
}
