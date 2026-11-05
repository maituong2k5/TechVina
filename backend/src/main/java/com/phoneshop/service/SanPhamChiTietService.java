
package com.phoneshop.service;

import org.springframework.stereotype.Service;

import com.phoneshop.dto.SanPhamChiTietDTO;
import com.phoneshop.entity.SanPham;
import com.phoneshop.repository.SanPhamRepository;

/**
 * Service xử lý thông tin chi tiết cơ bản của sản phẩm.
 */
@Service
public class SanPhamChiTietService {

	private final SanPhamRepository sanPhamRepository;

	// Constructor Injection Repository.
	public SanPhamChiTietService(SanPhamRepository sanPhamRepository) {
		this.sanPhamRepository = sanPhamRepository;
	}

	/**
	 * Lấy thông tin sản phẩm theo mã sản phẩm.
	 *
	 * @param maSP mã sản phẩm
	 * @return DTO chứa tên sản phẩm, hãng và danh mục
	 */
	public SanPhamChiTietDTO layThongTinSanPham(Integer maSP) {

		// Tìm sản phẩm cùng thông tin hãng và danh mục.
		SanPham sanPham = sanPhamRepository.timSanPhamChiTiet(maSP)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm có mã: " + maSP));

		// Chuyển Entity sang DTO.
		return new SanPhamChiTietDTO(sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getHang().getTenHang(),
				sanPham.getDanhMuc().getTenDanhMuc());
	}
}
