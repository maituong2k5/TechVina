
package com.phoneshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phoneshop.entity.KhachHang;

/**
 * Repository quản lý truy vấn dữ liệu khách hàng. JpaRepository cung cấp sẵn
 * các thao tác CRUD.
 */
@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {

	// Tìm khách hàng theo email khi đăng nhập.
	Optional<KhachHang> findByEmailAndTrangThai(String email, Boolean trangThai);

	// Tìm khách hàng theo email.
	Optional<KhachHang> findByEmail(String email);

	// Kiểm tra email đã được sử dụng hay chưa.
	boolean existsByEmail(String email);

	// Kiểm tra số điện thoại đã được sử dụng hay chưa.
	boolean existsBySoDienThoai(String soDienThoai);
}
