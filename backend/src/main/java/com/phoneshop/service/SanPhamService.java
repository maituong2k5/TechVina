package com.phoneshop.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.phoneshop.dto.SanPhamNoiBatDTO;
import com.phoneshop.entity.Anh;
import com.phoneshop.entity.ChiTietGiamGia;
import com.phoneshop.entity.GiamGia;
import com.phoneshop.entity.LichSuGia;
import com.phoneshop.entity.PhienBan;
import com.phoneshop.entity.SanPham;
import com.phoneshop.repository.AnhRepository;
import com.phoneshop.repository.ChiTietGiamGiaRepository;
import com.phoneshop.repository.GiamGiaRepository;
import com.phoneshop.repository.LichSuGiaRepository;
import com.phoneshop.repository.PhienBanRepository;
import com.phoneshop.repository.SanPhamRepository;
import com.phoneshop.repository.TonKhoRepository;

/**
 * Service xử lý nghiệp vụ liên quan đến sản phẩm.
 */
@Service
public class SanPhamService {

	// Repository dùng để truy vấn dữ liệu sản phẩm
	private final SanPhamRepository sanPhamRepository;

	// Repository dùng để truy vấn các phiên bản sản phẩm
	private final PhienBanRepository phienBanRepository;

	// Repository truy vấn hình ảnh sản phẩm
	private final AnhRepository anhRepository;

	// Repository truy vấn lịch sử giá
	private final LichSuGiaRepository lichSuGiaRepository;

	// Repository truy vấn tồn kho
	private final TonKhoRepository tonKhoRepository;

	// Repository truy vấn chương trình giảm giá
	private final GiamGiaRepository giamGiaRepository;

	// Repository truy vấn chi tiết giảm giá theo phiên bản
	private final ChiTietGiamGiaRepository chiTietGiamGiaRepository;

	public SanPhamService(SanPhamRepository sanPhamRepository, PhienBanRepository phienBanRepository,
			AnhRepository anhRepository, LichSuGiaRepository lichSuGiaRepository, TonKhoRepository tonKhoRepository,
			GiamGiaRepository giamGiaRepository, ChiTietGiamGiaRepository chiTietGiamGiaRepository) {

		this.sanPhamRepository = sanPhamRepository;
		this.phienBanRepository = phienBanRepository;
		this.anhRepository = anhRepository;
		this.lichSuGiaRepository = lichSuGiaRepository;
		this.tonKhoRepository = tonKhoRepository;
		this.giamGiaRepository = giamGiaRepository;
		this.chiTietGiamGiaRepository = chiTietGiamGiaRepository;
	}

	/**
	 * Lấy danh sách phiên bản nổi bật đang được kinh doanh.
	 */
	public List<PhienBan> layPhienBanNoiBat() {
		return phienBanRepository.findByNoiBatTrueAndSanPham_TrangThaiTrueOrderByMaPBAsc();
	}

	/**
	 * Lấy danh sách sản phẩm nổi bật kèm thông tin phiên bản đại diện.
	 *
	 * Quy tắc: - Chỉ chọn phiên bản có ảnh. - Lấy ảnh có thứ tự hiển thị nhỏ nhất.
	 * - Lấy giá hiện tại của phiên bản. - Tính tổng tồn kho tại tất cả các kho.
	 */
	public List<SanPhamNoiBatDTO> layDanhSachSanPhamNoiBat() {

		// Lấy danh sách phiên bản nổi bật thuộc sản phẩm đang hoạt động
		List<PhienBan> phienBanList = phienBanRepository.findByNoiBatTrueAndSanPham_TrangThaiTrueOrderByMaPBAsc();

		// Danh sách DTO trả về Frontend
		List<SanPhamNoiBatDTO> ketQua = new java.util.ArrayList<>();

		for (PhienBan phienBan : phienBanList) {

			SanPham sanPham = phienBan.getSanPham();

			// Tìm phiên bản đầu tiên có ảnh

			// Lấy ảnh theo thứ tự hiển thị
			List<Anh> anhList = anhRepository.findByPhienBan_MaPBOrderByThuTuHienThiAsc(phienBan.getMaPB());

			// Bỏ qua phiên bản chưa có ảnh
			if (anhList.isEmpty()) {
				continue;
			}

			// Lấy ảnh đại diện
			String anhDaiDien = anhList.get(0).getDuongDanAnh();

			// Lấy giá hiện tại
			Optional<LichSuGia> lichSuGia = lichSuGiaRepository
					.findFirstByPhienBan_MaPBAndNgayKetThucIsNull(phienBan.getMaPB());

			// Bỏ qua phiên bản chưa có giá hiện tại
			if (lichSuGia.isEmpty()) {
				continue;
			}

			// Lấy tổng số lượng tồn kho
			Long soLuongTon = tonKhoRepository.tinhTongSoLuongTon(phienBan.getMaPB());

			// Lấy cấu hình RAM và ROM
			if (phienBan.getCauHinhDungLuong() == null || phienBan.getCauHinhDungLuong().getRam() == null
					|| phienBan.getCauHinhDungLuong().getRom() == null) {
				continue;
			}

			Integer dungLuongRam = phienBan.getCauHinhDungLuong().getRam().getDungLuong();

			Integer dungLuongRom = phienBan.getCauHinhDungLuong().getRom().getDungLuong();

			// Lấy tên thương hiệu và danh mục an toàn
			String tenHang = sanPham.getHang() != null ? sanPham.getHang().getTenHang() : "Chưa cập nhật";

			String tenDanhMuc = sanPham.getDanhMuc() != null ? sanPham.getDanhMuc().getTenDanhMuc() : "Chưa phân loại";

			// Lấy giá gốc từ lịch sử giá hiện tại
			BigDecimal giaGoc = lichSuGia.get().getGia();

			// Mặc định sản phẩm không có khuyến mãi
			BigDecimal giaTriGiam = BigDecimal.ZERO;

			// Lấy danh sách chương trình giảm giá đang áp dụng cho phiên bản
			List<ChiTietGiamGia> danhSachGiamGia = chiTietGiamGiaRepository
					.findKhuyenMaiDangHoatDong(phienBan.getMaPB(), LocalDateTime.now());

			// Tìm mức giảm tốt nhất nếu có nhiều chương trình
			for (ChiTietGiamGia chiTiet : danhSachGiamGia) {

				// Lấy thông tin chương trình giảm giá
				GiamGia giamGia = chiTiet.getGiamGia();

				// Ưu tiên mức giảm riêng của phiên bản
				// Nếu không có thì sử dụng mức giảm chung
				BigDecimal mucGiam = chiTiet.getGiaTriGiamRieng() != null ? chiTiet.getGiaTriGiamRieng()
						: giamGia.getGiaTriGiam();

				BigDecimal soTienGiam;

				// Xử lý giảm giá theo phần trăm
				if ("PERCENT".equalsIgnoreCase(giamGia.getLoaiGiamGia())) {

					soTienGiam = giaGoc.multiply(mucGiam).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

				}
				// Xử lý giảm giá theo số tiền cố định
				else if ("FIXED".equalsIgnoreCase(giamGia.getLoaiGiamGia())) {

					soTienGiam = mucGiam;

				} else {
					continue;
				}

				// Không cho phép giảm quá giá gốc hoặc giảm số tiền âm
				soTienGiam = soTienGiam.max(BigDecimal.ZERO).min(giaGoc);

				// Chỉ giữ lại chương trình có mức giảm tiền lớn nhất
				if (soTienGiam.compareTo(giaTriGiam) > 0) {
					giaTriGiam = soTienGiam;
				}
			}

			// Tính giá sau giảm
			BigDecimal giaSauGiam = giaGoc.subtract(giaTriGiam);

			// Tính phần trăm giảm để hiển thị trên Frontend
			BigDecimal phanTramGiam = BigDecimal.ZERO;

			// Tính phần trăm giảm và làm tròn thành số nguyên
			if (giaGoc.compareTo(BigDecimal.ZERO) > 0) {
				phanTramGiam = giaTriGiam.multiply(new BigDecimal("100")).divide(giaGoc, 0, RoundingMode.HALF_UP);
			}

			// Tạo DTO chứa thông tin sản phẩm
			SanPhamNoiBatDTO dto = new SanPhamNoiBatDTO(sanPham.getMaSP(), phienBan.getMaPB(), sanPham.getTenSP(),
					tenHang, tenDanhMuc, anhDaiDien, giaGoc, // giaGoc
					giaSauGiam, // giaSauGiam
					giaTriGiam, // giaTriGiam
					phanTramGiam, // phanTramGiam
					dungLuongRam + "GB", dungLuongRom + "GB", soLuongTon);

			// Thêm phiên bản nổi bật hiện tại vào danh sách DTO
			ketQua.add(dto);
		}

		return ketQua;
	}

}