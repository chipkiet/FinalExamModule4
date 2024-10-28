package org.example.quanlydonhangs.Repository;

import org.example.quanlydonhangs.Model.DonHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Long> {

    @Query(value = "SELECT * FROM DonHang d WHERE d.ngay_mua BETWEEN :startDate AND :endDate", nativeQuery = true)
    Page<DonHang> findOrdersByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);


    @Query(value = "SELECT d.*, (s.gia_sp * d.so_luong) AS totalAmount " +
            "FROM DonHang d " +
            "JOIN SanPham s ON d.ma_sp = s.ma_sp " +
            "ORDER BY totalAmount DESC", nativeQuery = true)
    Page<DonHang> findTopOrders(Pageable pageable);


}