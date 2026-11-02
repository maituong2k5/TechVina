package com.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phoneshop.entity.SanPham;

/**
 * Repository dùng để thao tác với bảng SanPham.
 *
 * JpaRepository cung cấp sẵn các chức năng CRUD: - findAll() - findById() -
 * save() - deleteById()
 */

public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {

}
