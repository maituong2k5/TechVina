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

	/**
	 * Thêm một danh mục mới vào CSDL.
	 *
	 * @param danhMuc danh mục cần thêm
	 * @return danh mục sau khi lưu
	 */
	public DanhMuc createDanhMuc(DanhMuc danhMuc) {
		return danhMucRepository.save(danhMuc);
	}

	/**
	 * Cập nhật thông tin danh mục.
	 *
	 * @param id      mã danh mục cần cập nhật
	 * @param danhMuc dữ liệu mới
	 * @return danh mục sau khi cập nhật
	 */
	public DanhMuc updateDanhMuc(Integer id, DanhMuc danhMuc) {

	    // Tìm danh mục hiện tại theo ID
	    DanhMuc danhMucCu = danhMucRepository.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(
	                    HttpStatus.NOT_FOUND,
	                    "Không tìm thấy danh mục có ID: " + id
	            ));

	    // Cập nhật tên danh mục
	    danhMucCu.setTenDanhMuc(danhMuc.getTenDanhMuc());

	    // Cập nhật đường dẫn ảnh
	    danhMucCu.setDuongDanAnh(danhMuc.getDuongDanAnh());

	    // Cập nhật thứ tự hiển thị
	    danhMucCu.setThuTuHienThi(danhMuc.getThuTuHienThi());

	    // Cập nhật trạng thái
	    danhMucCu.setTrangThai(danhMuc.getTrangThai());

	    // Lưu thông tin đã cập nhật
	    return danhMucRepository.save(danhMucCu);
	}

	/**
	 * Xóa danh mục theo ID.
	 *
	 * @param id mã danh mục cần xóa
	 */
	public void deleteDanhMuc(Integer id) {

		// Kiểm tra danh mục có tồn tại hay không
		if (!danhMucRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy danh mục có ID: " + id);
		}

		// Xóa danh mục
		danhMucRepository.deleteById(id);
	}
	
	/**
	 * Lấy danh sách danh mục đang hoạt động,
	 * sắp xếp theo thứ tự hiển thị tăng dần.
	 *
	 * @return danh sách danh mục đang hoạt động
	 */
	public List<DanhMuc> getDanhMucDangHoatDong() {
	    return danhMucRepository.findByTrangThaiTrueOrderByThuTuHienThiAsc();
	}
}
