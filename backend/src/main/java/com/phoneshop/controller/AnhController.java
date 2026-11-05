
package com.phoneshop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoneshop.dto.AnhDTO;
import com.phoneshop.service.AnhService;

/**
 * Controller cung cấp API hình ảnh sản phẩm.
 */
@RestController
@RequestMapping("/api/phien-ban")
public class AnhController {

	private final AnhService anhService;

	// Constructor Injection Service.
	public AnhController(AnhService anhService) {
		this.anhService = anhService;
	}

	/**
	 * Lấy danh sách hình ảnh theo mã phiên bản.
	 */
	@GetMapping("/{maPB}/anh")
	public ResponseEntity<List<AnhDTO>> layDanhSachAnh(@PathVariable Integer maPB) {

		List<AnhDTO> ketQua = anhService.layDanhSachAnh(maPB);

		return ResponseEntity.ok(ketQua);
	}
}
