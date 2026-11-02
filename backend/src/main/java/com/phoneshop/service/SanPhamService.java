package com.phoneshop.service;

import org.springframework.stereotype.Service;

import com.phoneshop.repository.SanPhamRepository;

/**
 * Service xử lý nghiệp vụ liên quan đến SanPham.
 */

@Service
public class SanPhamService {
	private final SanPhamRepository sanPhamRepository;

	/**
	 * Constructor Injection.
	 *
	 * Spring sẽ tự động inject SanPhamRepository vào Service.
	 */

	public SanPhamService(SanPhamRepository sanPhamRepository) {
		this.sanPhamRepository = sanPhamRepository;
	}

	/**
	 * Lấy tất cả sản phẩm từ CSDL.
	 *
	 * @return danh sách sản phẩm
	 */

}
