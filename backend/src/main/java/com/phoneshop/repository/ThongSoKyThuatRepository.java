
package com.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phoneshop.entity.ThongSoKyThuat;

/**
 * Repository quản lý truy vấn thông số kỹ thuật.
 */
@Repository
public interface ThongSoKyThuatRepository extends JpaRepository<ThongSoKyThuat, Integer> {

	/**
	 * Lấy danh sách thông số thuộc một nhóm. Sắp xếp theo thứ tự hiển thị tăng dần.
	 */
	List<ThongSoKyThuat> findByNhomThongSo_MaNhomOrderByThuTuHienThiAsc(Integer maNhom);
}
