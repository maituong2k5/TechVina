
package com.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phoneshop.entity.ChiTietThongSo;

/**
 * Repository quản lý truy vấn chi tiết thông số kỹ thuật.
 */
@Repository
public interface ChiTietThongSoRepository extends JpaRepository<ChiTietThongSo, Integer> {

	/**
	 * Lấy toàn bộ thông số kỹ thuật của một sản phẩm.
	 */
	List<ChiTietThongSo> findBySanPham_MaSP(Integer maSP);

	/**
	 * Lấy thông số của sản phẩm. Sắp xếp theo thứ tự nhóm và thứ tự thông số tăng
	 * dần.
	 */
	List<ChiTietThongSo> findBySanPham_MaSPOrderByThongSoKyThuat_NhomThongSo_ThuTuHienThiAscThongSoKyThuat_ThuTuHienThiAsc(
			Integer maSP);
}
