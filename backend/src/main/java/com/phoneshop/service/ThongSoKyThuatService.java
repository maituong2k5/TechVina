
package com.phoneshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.phoneshop.dto.NhomThongSoDTO;
import com.phoneshop.dto.ThongSoKyThuatDTO;
import com.phoneshop.entity.ChiTietThongSo;
import com.phoneshop.repository.ChiTietThongSoRepository;

/**
 * Service xử lý nghiệp vụ liên quan đến thông số kỹ thuật sản phẩm.
 */
@Service
public class ThongSoKyThuatService {

	// Repository truy vấn thông số kỹ thuật của sản phẩm
	private final ChiTietThongSoRepository chiTietThongSoRepository;

	/**
	 * Constructor Injection để khởi tạo Repository.
	 */
	public ThongSoKyThuatService(ChiTietThongSoRepository chiTietThongSoRepository) {
		this.chiTietThongSoRepository = chiTietThongSoRepository;
	}

	/**
	 * Lấy danh sách thông số kỹ thuật theo mã sản phẩm.
	 */
	public List<NhomThongSoDTO> layThongSoTheoSanPham(Integer maSP) {

		// Lấy thông số kỹ thuật và sắp xếp theo thứ tự hiển thị
		List<ChiTietThongSo> danhSachChiTiet = chiTietThongSoRepository
				.findBySanPham_MaSPOrderByThongSoKyThuat_NhomThongSo_ThuTuHienThiAscThongSoKyThuat_ThuTuHienThiAsc(
						maSP);

		// Danh sách nhóm thông số trả về Frontend
		List<NhomThongSoDTO> ketQua = new ArrayList<>();

		// Duyệt từng thông số kỹ thuật của sản phẩm
		for (ChiTietThongSo chiTiet : danhSachChiTiet) {

			// Lấy tên nhóm thông số
			String tenNhom = chiTiet.getThongSoKyThuat().getNhomThongSo().getTenNhom();

			// Lấy tên thông số
			String tenThongSo = chiTiet.getThongSoKyThuat().getTenThongSo();

			// Lấy đơn vị đo của thông số
			String donVi = chiTiet.getThongSoKyThuat().getDonVi();

			// Lấy giá trị thông số
			String giaTri = chiTiet.getGiaTri();

			// Tìm nhóm đã tồn tại trong danh sách kết quả
			NhomThongSoDTO nhomDTO = ketQua.stream().filter(nhom -> nhom.getTenNhom().equals(tenNhom)).findFirst()
					.orElse(null);

			// Nếu nhóm chưa tồn tại thì tạo nhóm mới
			if (nhomDTO == null) {
				nhomDTO = new NhomThongSoDTO(tenNhom, new ArrayList<>());

				ketQua.add(nhomDTO);
			}

			// Tạo DTO cho thông số hiện tại
			ThongSoKyThuatDTO thongSoDTO = new ThongSoKyThuatDTO(tenThongSo, giaTri, donVi);

			// Thêm thông số vào đúng nhóm
			nhomDTO.getDanhSachThongSo().add(thongSoDTO);
		}

		return ketQua;
	}
}
