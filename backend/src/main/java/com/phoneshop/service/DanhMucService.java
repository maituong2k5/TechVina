package com.phoneshop.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.phoneshop.entity.DanhMuc;
import com.phoneshop.repository.DanhMucRepository;

/**
 * Service xử lý nghiệp vụ liên quan đến DanhMuc.
 */
@Service
public class DanhMucService {
	// Repository dùng để truy vấn dữ liệu DanhMuc từ CSDL
	private final DanhMucRepository danhMucRepository;

	/**
	 * Constructor Injection.
	 *
	 * Spring sẽ tự động inject DanhMucRepository vào Service.
	 */
	public DanhMucService(DanhMucRepository danhMucRepository) {
		this.danhMucRepository = danhMucRepository;
	}

	/**
	 * Lấy tất cả danh mục từ CSDL.
	 *
	 * @return danh sách danh mục
	 */
	public List<DanhMuc> getAllDanhMuc() {
		return danhMucRepository.findAll();
	}

	public DanhMuc getDanhMucById(Integer id) {
		return danhMucRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy danh mục có mã: " + id));
	}
}
