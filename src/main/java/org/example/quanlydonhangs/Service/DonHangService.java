package org.example.quanlydonhangs.Service;

import org.example.quanlydonhangs.Model.DonHang;
import org.example.quanlydonhangs.Repository.DonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonHangService {

    @Autowired
    private DonHangRepository donHangRepository;

    public List<DonHang> layTatCaDonHang() {
        return donHangRepository.findAll();
    }

    public List<DonHang> layDonHangTheoNgay(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
        }
        return donHangRepository.findOrdersByDateRange(startDate, endDate);
    }

    public List<DonHang> layTopDonHang(int topCount) {
        List<DonHang> allOrders = donHangRepository.findTopOrders();
        return allOrders.stream().limit(topCount).toList();
    }



}