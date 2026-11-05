package com.phoneshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phoneshop.entity.SanPham;

/**
 * Repository dùng để thao tác với bảng SanPham.
 */
@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {

	/**
	 * Tìm sản phẩm theo mã sản phẩm, đồng thời lấy thông tin hãng và danh mục.
	 */
	@Query("""
			    SELECT sp
			    FROM SanPham sp
			    JOIN FETCH sp.hang
			    JOIN FETCH sp.danhMuc
			    WHERE sp.maSP = :maSP
			""")
	Optional<SanPham> timSanPhamChiTiet(@Param("maSP") Integer maSP);

}