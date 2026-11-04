package com.phoneshop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.phoneshop.entity.ChiTietGiamGia;

/**
 * Repository xử lý truy vấn chi tiết chương trình giảm giá.
 */
public interface ChiTietGiamGiaRepository
        extends JpaRepository<ChiTietGiamGia, Integer> {

    /**
     * Lấy các chương trình giảm giá đang áp dụng
     * cho một phiên bản sản phẩm tại thời điểm hiện tại.
     */
    @Query("""
            SELECT ct
            FROM ChiTietGiamGia ct
            JOIN FETCH ct.giamGia gg
            WHERE ct.phienBan.maPB = :maPB
              AND gg.trangThai = true
              AND gg.ngayBatDau <= :thoiGianHienTai
              AND gg.ngayKetThuc >= :thoiGianHienTai
            """)
    List<ChiTietGiamGia> findKhuyenMaiDangHoatDong(
            @Param("maPB") Integer maPB,
            @Param("thoiGianHienTai") LocalDateTime thoiGianHienTai
    );
}