package com.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phoneshop.entity.SanPham;

/**
 * Repository dùng để thao tác với bảng SanPham.
 */
@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {

    /**
     * Lấy danh sách sản phẩm đang hoạt động và được đánh dấu nổi bật.
     */	
	
    List<SanPham> findByTrangThaiTrueAndNoiBatTrue();
    
}