package com.phoneshop.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phoneshop.entity.TonKho;

@Repository
public interface TonKhoRepository extends JpaRepository<TonKho, Integer> {
	 /**
     * Tính tổng số lượng tồn của một phiên bản
     * tại tất cả các kho.
     */
    @Query("""
        SELECT COALESCE(SUM(tk.soLuongTon), 0)
        FROM TonKho tk
        WHERE tk.phienBan.maPB = :maPB
    """)
    Long tinhTongSoLuongTon(@Param("maPB") Integer maPB);
}
