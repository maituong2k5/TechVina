package com.phoneshop.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phoneshop.entity.Anh;

@Repository
public interface AnhRepository extends JpaRepository<Anh, Integer> {
	  /**
     * Lấy danh sách ảnh của một phiên bản.
     * Sắp xếp theo thứ tự hiển thị tăng dần.
     */
    List<Anh> findByPhienBan_MaPBOrderByThuTuHienThiAsc(Integer maPB);
}	
