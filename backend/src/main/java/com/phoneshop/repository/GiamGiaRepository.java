package com.phoneshop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.phoneshop.entity.GiamGia;

/**
 * Repository xử lý truy vấn dữ liệu chương trình giảm giá.
 */
public interface GiamGiaRepository extends JpaRepository<GiamGia, Integer> {

    /**
     * Lấy danh sách chương trình giảm giá đang hoạt động.
     *
     * Điều kiện:
     * - Trạng thái phải đang bật.
     * - Thời điểm hiện tại nằm trong thời gian áp dụng.
     */
    @Query("""
            SELECT gg
            FROM GiamGia gg
            WHERE gg.trangThai = true
              AND gg.ngayBatDau <= :thoiGianHienTai
              AND gg.ngayKetThuc >= :thoiGianHienTai
            """)
    List<GiamGia> findGiamGiaDangHoatDong(
            @Param("thoiGianHienTai") LocalDateTime thoiGianHienTai
    );
}