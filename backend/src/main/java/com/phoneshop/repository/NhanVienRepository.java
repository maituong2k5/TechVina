
package com.phoneshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phoneshop.entity.NhanVien;

/**
 * Repository quản lý truy vấn dữ liệu nhân viên. JpaRepository cung cấp sẵn các
 * thao tác CRUD.
 */
@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {

	// Tìm nhân viên theo email khi đăng nhập.
	Optional<NhanVien> findByEmail(String email);

	// Kiểm tra email đã được sử dụng hay chưa.
	boolean existsByEmail(String email);
}
