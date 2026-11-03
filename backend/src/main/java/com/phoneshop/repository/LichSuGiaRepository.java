package com.phoneshop.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phoneshop.entity.LichSuGia;

@Repository
public interface LichSuGiaRepository extends JpaRepository<LichSuGiaRepository, Integer>  {
	/**
     * Lấy mức giá hiện tại của một phiên bản sản phẩm.
     * Mức giá hiện tại có NgayKetThuc = NULL.
     */
    Optional<LichSuGiaRepository> findFirstByPhienBan_MaPBAndNgayKetThucIsNull(
            Integer maPB
    );
}
