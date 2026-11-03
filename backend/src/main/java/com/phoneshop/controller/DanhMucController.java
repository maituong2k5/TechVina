package com.phoneshop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	/**
	 * API lấy danh sách danh mục đang hoạt động.
	 *
	 * GET /api/danh-muc/active
	 *
	 * @return danh sách danh mục đang hoạt động
	 */
	@GetMapping("/active")
	public List<DanhMuc> getDanhMucDangHoatDong() {
	    return danhMucService.getDanhMucDangHoatDong();
	}

	/**
	 * API thêm một danh mục mới.
	 *
	 * POST /api/danh-muc
	 *
	 * @param danhMuc thông tin danh mục cần thêm
	 * @return danh mục sau khi lưu vào CSDL
	 */
	@PostMapping
	public DanhMuc createDanhMuc(@RequestBody DanhMuc danhMuc) {
		return danhMucService.createDanhMuc(danhMuc);
	}

	/**
	 * API cập nhật danh mục theo ID.
	 *
	 * PUT /api/danh-muc/{id}
	 *
	 * @param id      ID của danh mục cần cập nhật
	 * @param danhMuc dữ liệu mới
	 * @return danh mục sau khi cập nhật
	 */
	@PutMapping("/{id}")
	public DanhMuc updateDanhMuc(@PathVariable Integer id, @RequestBody DanhMuc danhMuc) {

		return danhMucService.updateDanhMuc(id, danhMuc);
	}

	/**
	 * API xóa danh mục theo ID.
	 *
	 * DELETE /api/danh-muc/{id}
	 *
	 * @param id mã danh mục cần xóa
	 */
	@DeleteMapping("/{id}")
	public void deleteDanhMuc(@PathVariable Integer id) {
		danhMucService.deleteDanhMuc(id);
	}
	
	
}
