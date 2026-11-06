package com.phoneshop.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phoneshop.entity.RefreshToken;

/**
 * Repository quản lý dữ liệu RefreshToken.
 *
 * Kế thừa JpaRepository để sử dụng các thao tác CRUD.
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
	// Tìm Refresh Token dựa trên giá trị Hash.
	Optional<RefreshToken> findByTokenHash(String tokenHash);

	// Tìm Refresh Token còn hiệu lực của khách hàng.
	Optional<RefreshToken> findByTokenHashAndNgayThuHoiIsNullAndNgayHetHanAfter(String tokenHash,
			LocalDateTime thoiDiemHienTai);

	// Lấy danh sách Refresh Token của một khách hàng.
	List<RefreshToken> findByKhachHang_MaKH(Integer maKH);

	// Lấy danh sách Refresh Token của một nhân viên.
	List<RefreshToken> findByNhanVien_MaNhanVien(Integer maNhanVien);
}