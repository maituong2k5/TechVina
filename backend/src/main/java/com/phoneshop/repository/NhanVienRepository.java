
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

	/**
	 * Tìm nhân viên bằng Cognito User ID (sub).
	 *
	 * Sử dụng sau khi Spring Security xác thực Access Token do Amazon Cognito cấp.
	 *
	 * Phương thức hỗ trợ liên kết tài khoản ADMIN hoặc STAFF với dữ liệu trong
	 * MySQL.
	 */
	Optional<NhanVien> findByCognitoSub(String cognitoSub);

	/**
	 * Tìm nhân viên đang hoạt động bằng CognitoSub.
	 *
	 * Chỉ trả về nhân viên có TrangThai = true.
	 *
	 * Giúp ngăn tài khoản nhân viên đã bị vô hiệu hóa truy cập các chức năng nghiệp
	 * vụ qua liên kết này.
	 */
	Optional<NhanVien> findByCognitoSubAndTrangThai(String cognitoSub, Boolean trangThai);

}
