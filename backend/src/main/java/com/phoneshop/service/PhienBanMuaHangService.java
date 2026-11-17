package com.phoneshop.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.phoneshop.dto.PhienBanMuaHangDTO;
import com.phoneshop.entity.ChiTietGiamGia;
import com.phoneshop.entity.LichSuGia;
import com.phoneshop.entity.PhienBan;
import com.phoneshop.repository.ChiTietGiamGiaRepository;
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
	private final ChiTietGiamGiaRepository chiTietGiamGiaRepository;

	// Constructor Injection các Repository cần sử dụng.
	public PhienBanMuaHangService(PhienBanRepository phienBanRepository, LichSuGiaRepository lichSuGiaRepository,
			TonKhoRepository tonKhoRepository, ChiTietGiamGiaRepository chiTietGiamGiaRepository) {

		this.phienBanRepository = phienBanRepository;
		this.lichSuGiaRepository = lichSuGiaRepository;
		this.tonKhoRepository = tonKhoRepository;
		this.chiTietGiamGiaRepository = chiTietGiamGiaRepository;
	}

	/**
	 * Lấy danh sách phiên bản cùng giá và tồn kho theo mã sản phẩm.
	 */
	public List<PhienBanMuaHangDTO> layDanhSachPhienBan(Integer maSP) {

		List<PhienBan> danhSachPhienBan = phienBanRepository.findBySanPham_MaSPOrderByMaPBAsc(maSP);

		List<PhienBanMuaHangDTO> ketQua = new ArrayList<>();

		LocalDateTime thoiGianHienTai = LocalDateTime.now();

		for (PhienBan phienBan : danhSachPhienBan) {

			Integer maPB = phienBan.getMaPB();

			// Lấy giá bán hiện tại.
			Optional<LichSuGia> lichSuGia = lichSuGiaRepository.findFirstByPhienBan_MaPBAndNgayKetThucIsNull(maPB);

			BigDecimal giaGoc = lichSuGia.map(LichSuGia::getGia).orElse(null);

			Long soLuongTon = tonKhoRepository.tinhTongSoLuongTon(maPB);

			// Tạo DTO với các thông tin hiện tại.
			PhienBanMuaHangDTO dto = new PhienBanMuaHangDTO(maPB, phienBan.getSku(),
					phienBan.getCauHinhDungLuong().getRam().getDungLuong(),
					phienBan.getCauHinhDungLuong().getRom().getDungLuong(), phienBan.getMauSac().getTenMau(), giaGoc,
					soLuongTon);

			// Giá chưa có hoặc không hợp lệ.
			if (giaGoc == null || giaGoc.compareTo(BigDecimal.ZERO) <= 0) {
				dto.setGiaSauGiam(giaGoc);
				dto.setPhanTramGiam(BigDecimal.ZERO);
				dto.setCoGiamGia(false);
				ketQua.add(dto);
				continue;
			}

			// Lấy các chương trình giảm giá đang hoạt động.
			List<ChiTietGiamGia> danhSachGiamGia = chiTietGiamGiaRepository.findKhuyenMaiDangHoatDong(maPB,
					thoiGianHienTai);

			BigDecimal mucGiamLonNhat = BigDecimal.ZERO;

			for (ChiTietGiamGia chiTiet : danhSachGiamGia) {

				String loaiGiamGia = chiTiet.getGiamGia().getLoaiGiamGia();

				// Ưu tiên giá trị giảm riêng của phiên bản.
				BigDecimal giaTriGiam = chiTiet.getGiaTriGiamRieng() != null ? chiTiet.getGiaTriGiamRieng()
						: chiTiet.getGiamGia().getGiaTriGiam();

				if (giaTriGiam == null || giaTriGiam.compareTo(BigDecimal.ZERO) <= 0) {
					continue;
				}

				BigDecimal soTienGiam;

				if ("PERCENT".equalsIgnoreCase(loaiGiamGia)) {

					// Không cho phép giảm vượt 100%.
					BigDecimal phanTram = giaTriGiam.min(new BigDecimal("100"));

					soTienGiam = giaGoc.multiply(phanTram).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

				} else if ("FIXED".equalsIgnoreCase(loaiGiamGia)) {

					soTienGiam = giaTriGiam;

				} else {
					continue;
				}

				// Không giảm vượt quá giá bán.
				soTienGiam = soTienGiam.min(giaGoc);

				// Chọn mức giảm lớn nhất.
				if (soTienGiam.compareTo(mucGiamLonNhat) > 0) {
					mucGiamLonNhat = soTienGiam;
				}
			}

			// Tính giá sau giảm.
			BigDecimal giaSauGiam = giaGoc.subtract(mucGiamLonNhat);

			// Tính phần trăm giảm thực tế.
			BigDecimal phanTramGiam = mucGiamLonNhat.multiply(new BigDecimal("100")).divide(giaGoc, 2,
					RoundingMode.HALF_UP);

			dto.setGiaSauGiam(giaSauGiam);
			dto.setPhanTramGiam(phanTramGiam);
			dto.setCoGiamGia(mucGiamLonNhat.compareTo(BigDecimal.ZERO) > 0);

			ketQua.add(dto);
		}

		return ketQua;
	}

}