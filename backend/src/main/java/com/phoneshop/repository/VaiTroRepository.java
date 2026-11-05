
package com.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phoneshop.entity.VaiTro;

/**
 * Repository quản lý truy vấn dữ liệu vai trò.
 */
@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, Integer> {

	// Tìm vai trò theo tên: CUSTOMER, STAFF hoặc ADMIN.
	VaiTro findByTenVaiTro(String tenVaiTro);
}
