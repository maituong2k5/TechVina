package com.phoneshop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoneshop.dto.PhienBanMuaHangDTO;
import com.phoneshop.repository.SanPhamRepository;
import com.phoneshop.service.PhienBanMuaHangService;

/**
 * Controller cung cấp API thông tin phiên bản phục vụ mua hàng.
 */
@RestController
@RequestMapping("/api/san-pham")
public class PhienBanMuaHangController {

	private final PhienBanMuaHangService phienBanMuaHangService;
	private final SanPhamRepository sanPhamRepository;

	// Constructor Injection.
	public PhienBanMuaHangController(PhienBanMuaHangService phienBanMuaHangService,
			SanPhamRepository sanPhamRepository) {

		this.phienBanMuaHangService = phienBanMuaHangService;
		this.sanPhamRepository = sanPhamRepository;
	}

	/**
	 * Lấy danh sách phiên bản mua hàng theo mã sản phẩm.
	 */
	@GetMapping("/{maSP}/phien-ban")
	public ResponseEntity<List<PhienBanMuaHangDTO>> layDanhSachPhienBan(@PathVariable Integer maSP) {

		// Kiểm tra sản phẩm có tồn tại không.
		if (!sanPhamRepository.existsById(maSP)) {
			return ResponseEntity.notFound().build();
		}

		// Lấy danh sách phiên bản kèm giá và tồn kho.
		List<PhienBanMuaHangDTO> ketQua = phienBanMuaHangService.layDanhSachPhienBan(maSP);

		return ResponseEntity.ok(ketQua);
	}
}