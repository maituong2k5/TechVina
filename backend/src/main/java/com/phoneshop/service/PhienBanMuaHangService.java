package com.phoneshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.phoneshop.dto.PhienBanMuaHangDTO;
import com.phoneshop.entity.LichSuGia;
import com.phoneshop.entity.PhienBan;
import com.phoneshop.repository.LichSuGiaRepository;
import com.phoneshop.repository.PhienBanRepository;
import com.phoneshop.repository.TonKhoRepository;

/**
 * Service xử lý thông tin phiên bản phục vụ chức năng mua hàng.
 */
@Service
public class PhienBanMuaHangService {

	private final PhienBanRepository phienBanRepository;
	private final LichSuGiaRepository lichSuGiaRepository;
	private final TonKhoRepository tonKhoRepository;

	// Constructor Injection các Repository cần sử dụng.
	public PhienBanMuaHangService(PhienBanRepository phienBanRepository, LichSuGiaRepository lichSuGiaRepository,
			TonKhoRepository tonKhoRepository) {

		this.phienBanRepository = phienBanRepository;
		this.lichSuGiaRepository = lichSuGiaRepository;
		this.tonKhoRepository = tonKhoRepository;
	}

	/**
	 * Lấy danh sách phiên bản cùng giá và tồn kho theo mã sản phẩm.
	 */
	public List<PhienBanMuaHangDTO> layDanhSachPhienBan(Integer maSP) {

		// Lấy tất cả phiên bản thuộc sản phẩm.
		List<PhienBan> danhSachPhienBan = phienBanRepository.findBySanPham_MaSPOrderByMaPBAsc(maSP);

		List<PhienBanMuaHangDTO> ketQua = new ArrayList<>();

		// Duyệt từng phiên bản để lấy thông tin mua hàng.
		for (PhienBan phienBan : danhSachPhienBan) {

			Integer maPB = phienBan.getMaPB();

			// Lấy giá hiện tại của phiên bản.
			Optional<LichSuGia> lichSuGia = lichSuGiaRepository.findFirstByPhienBan_MaPBAndNgayKetThucIsNull(maPB);

			// Lấy tổng số lượng tồn tại tất cả các kho.
			Long soLuongTon = tonKhoRepository.tinhTongSoLuongTon(maPB);

			// Tạo DTO từ dữ liệu các Entity.
			PhienBanMuaHangDTO dto = new PhienBanMuaHangDTO(maPB, phienBan.getSku(),
					phienBan.getCauHinhDungLuong().getRam().getDungLuong(),
					phienBan.getCauHinhDungLuong().getRom().getDungLuong(), phienBan.getMauSac().getTenMau(),
					lichSuGia.map(LichSuGia::getGia).orElse(null), soLuongTon);

			ketQua.add(dto);
		}

		return ketQua;
	}
}