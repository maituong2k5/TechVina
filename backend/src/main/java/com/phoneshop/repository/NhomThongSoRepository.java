
package com.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phoneshop.entity.NhomThongSo;

/**
 * Repository quản lý truy vấn dữ liệu nhóm thông số.
 */
@Repository
public interface NhomThongSoRepository extends JpaRepository<NhomThongSo, Integer> {

}
