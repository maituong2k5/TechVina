package com.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phoneshop.entity.SanPham;

/**
 * Repository dùng để thao tác với bảng SanPham.
 */
@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {

}