package com.phoneshop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoneshop.entity.DanhMuc;
import com.phoneshop.service.DanhMucService;

/**
 * Controller cung cấp API liên quan đến DanhMuc.
 */
@RestController
@RequestMapping("/api/danh-muc")
public class DanhMucController {
	// Service xử lý nghiệp vụ danh mục
	private final DanhMucService danhMucService;

	/**
	 * Constructor Injection.
	 *
	 * Spring tự động inject DanhMucService vào Controller.
	 */
	public DanhMucController(DanhMucService danhMucService) {
		this.danhMucService = danhMucService;
	}

	/**
	 * API lấy toàn bộ danh mục.
	 *
	 * GET /api/danh-muc
	 *
	 * @return danh sách danh mục từ CSDL
	 */
	@GetMapping
	public List<DanhMuc> getAllDanhMuc() {
		return danhMucService.getAllDanhMuc();
	}

	/**
	 * API lấy một danh mục theo mã danh mục.
	 *
	 * GET /api/danh-muc/{id}
	 *
	 * Ví dụ: GET /api/danh-muc/1
	 *
	 * @param id mã danh mục
	 * @return danh mục tương ứng
	 */
	@GetMapping("/{id}")
	public DanhMuc getDanhMucById(@PathVariable Integer id) {
		return danhMucService.getDanhMucById(id);
	}
}
