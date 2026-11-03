package com.phoneshop.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import com.phoneshop.repository.SanPhamRepository;
import com.phoneshop.repository.TonKhoRepository;
import com.phoneshop.repository.AnhRepository;
import com.phoneshop.repository.LichSuGiaRepository;
import com.phoneshop.repository.PhienBanRepository;

import com.phoneshop.dto.SanPhamNoiBatDTO;
import com.phoneshop.entity.Anh;
import com.phoneshop.entity.LichSuGia;
import com.phoneshop.entity.PhienBan;
import com.phoneshop.entity.SanPham;

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

	public SanPhamService(SanPhamRepository sanPhamRepository, PhienBanRepository phienBanRepository,
			AnhRepository anhRepository, LichSuGiaRepository lichSuGiaRepository, TonKhoRepository tonKhoRepository) {

		this.sanPhamRepository = sanPhamRepository;
		this.phienBanRepository = phienBanRepository;
		this.anhRepository = anhRepository;
		this.lichSuGiaRepository = lichSuGiaRepository;
		this.tonKhoRepository = tonKhoRepository;
	}

	/**
	 * Lấy danh sách sản phẩm nổi bật đang hoạt động.
	 */
	public List<SanPham> laySanPhamNoiBat() {
		return sanPhamRepository.findByTrangThaiTrueAndNoiBatTrue();
	}

	/**
	 * Lấy danh sách sản phẩm nổi bật kèm thông tin phiên bản đại diện.
	 *
	 * Quy tắc: - Chỉ chọn phiên bản có ảnh. - Lấy ảnh có thứ tự hiển thị nhỏ nhất.
	 * - Lấy giá hiện tại của phiên bản. - Tính tổng tồn kho tại tất cả các kho.
	 */
	public List<SanPhamNoiBatDTO> layDanhSachSanPhamNoiBat() {

		// Lấy danh sách sản phẩm nổi bật đang hoạt động
		List<SanPham> sanPhamList = sanPhamRepository.findByTrangThaiTrueAndNoiBatTrue();

		// Danh sách DTO trả về Frontend
		List<SanPhamNoiBatDTO> ketQua = new java.util.ArrayList<>();

		for (SanPham sanPham : sanPhamList) {

			List<PhienBan> phienBanList =
			        phienBanRepository.findBySanPham_MaSPOrderByMaPBAsc(
			                sanPham.getMaSP()
			        );

			// Tìm phiên bản đầu tiên có ảnh
			for (PhienBan phienBan : phienBanList) {

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
				// Lấy cấu hình RAM và ROM
				if (phienBan.getCauHinhDungLuong() == null
				        || phienBan.getCauHinhDungLuong().getRam() == null
				        || phienBan.getCauHinhDungLuong().getRom() == null) {
				    continue;
				}

				Integer dungLuongRam = phienBan
				        .getCauHinhDungLuong()
				        .getRam()
				        .getDungLuong();

				Integer dungLuongRom = phienBan
				        .getCauHinhDungLuong()
				        .getRom()
				        .getDungLuong();
				
				// Lấy tên thương hiệu và danh mục an toàn
				String tenHang = sanPham.getHang() != null
				        ? sanPham.getHang().getTenHang()
				        : "Chưa cập nhật";

				String tenDanhMuc = sanPham.getDanhMuc() != null
				        ? sanPham.getDanhMuc().getTenDanhMuc()
				        : "Chưa phân loại";
				
				// Lấy giá gốc từ lịch sử giá hiện tại
				BigDecimal giaGoc = lichSuGia.get().getGia();

				// Tạm thời chưa áp dụng giảm giá
				// Sẽ tích hợp bảng GiamGia và ChiTietGiamGia ở bước tiếp theo
				BigDecimal giaTriGiam = BigDecimal.ZERO;
				BigDecimal giaSauGiam = giaGoc.subtract(giaTriGiam);
				BigDecimal phanTramGiam = BigDecimal.ZERO;
				// Tạo DTO chứa thông tin sản phẩm
				SanPhamNoiBatDTO dto = new SanPhamNoiBatDTO(
				        sanPham.getMaSP(),
				        sanPham.getTenSP(),
				        tenHang,
				        tenDanhMuc,
				        anhDaiDien,			   
				        giaGoc,         // giaGoc
				        giaSauGiam,     // giaSauGiam
				        giaTriGiam,     // giaTriGiam
				        phanTramGiam,   // phanTramGiam
				        dungLuongRam + "GB",
				        dungLuongRom + "GB",
				        soLuongTon
				);

				ketQua.add(dto);

				// Mỗi sản phẩm chỉ lấy một phiên bản đại diện
				break;
			}
		}

		return ketQua;
	}

}