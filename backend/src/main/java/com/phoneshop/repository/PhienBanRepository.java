package com.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phoneshop.entity.PhienBan;

@Repository
public interface PhienBanRepository extends JpaRepository<PhienBan, Integer> {

	/**
	 * Lấy danh sách phiên bản thuộc một sản phẩm. Sắp xếp theo mã phiên bản tăng
	 * dần.
	 */
	List<PhienBan> findBySanPham_MaSPOrderByMaPBAsc(Integer maSP);

	/**
	 * Lấy danh sách phiên bản được đánh dấu nổi bật và thuộc sản phẩm đang hoạt
	 * động.
	 *
	 * Sắp xếp theo mã phiên bản tăng dần.
	 */
	List<PhienBan> findByNoiBatTrueAndSanPham_TrangThaiTrueOrderByMaPBAsc();
}
