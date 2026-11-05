
package com.phoneshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.phoneshop.dto.AnhDTO;
import com.phoneshop.entity.Anh;
import com.phoneshop.repository.AnhRepository;

/**
 * Service xử lý nghiệp vụ hình ảnh sản phẩm.
 */
@Service
public class AnhService {

	private final AnhRepository anhRepository;

	// Constructor Injection Repository.
	public AnhService(AnhRepository anhRepository) {
		this.anhRepository = anhRepository;
	}

	/**
	 * Lấy danh sách hình ảnh theo mã phiên bản.
	 *
	 * @param maPB mã phiên bản sản phẩm
	 * @return danh sách ảnh đã sắp xếp
	 */
	public List<AnhDTO> layDanhSachAnh(Integer maPB) {

		// Truy vấn ảnh theo mã phiên bản.
		List<Anh> danhSachAnh = anhRepository.findByPhienBan_MaPBOrderByThuTuHienThiAsc(maPB);

		// Chuyển Entity sang DTO.
		return danhSachAnh.stream().map(anh -> new AnhDTO(anh.getMaAnh(), anh.getDuongDanAnh(), anh.getThuTuHienThi()))
				.toList();
	}
}
