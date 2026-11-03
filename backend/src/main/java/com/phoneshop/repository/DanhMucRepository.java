package com.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.phoneshop.entity.DanhMuc;

/**
 * Repository dùng để thao tác với bảng DanhMuc.
 *
 * JpaRepository cung cấp sẵn các chức năng CRUD: - findAll() - findById() -
 * save() - deleteById()
 */
public interface DanhMucRepository extends JpaRepository<DanhMuc, Integer> {
	List<DanhMuc> findByTrangThaiTrueOrderByThuTuHienThiAsc();	
	
}


	