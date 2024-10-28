package org.example.quanlydonhangs.Service;


import org.example.quanlydonhangs.Model.DonHang;
import org.example.quanlydonhangs.Repository.DonHangRepository;
import org.example.quanlydonhangs.Repository.LoaiSanPhamRepository;
import org.example.quanlydonhangs.Repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DonHangService {

    @Autowired
    private DonHangRepository donHangRepository;

    @Autowired
    private LoaiSanPhamRepository loaiSanPhamRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    public Page<DonHang> layTatCaDonHang(Pageable pageable) {
        return donHangRepository.findAll(pageable);
    }

    public Page<DonHang> layDonHangTheoNgay(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
        }
        return donHangRepository.findOrdersByDateRange(startDate, endDate, pageable);
    }
    public Page<DonHang> layTopDonHang(Pageable pageable) {
        return donHangRepository.findTopOrders(pageable);
    }

    public DonHang layDonHangTheoId(Long maDonHang) {
        return donHangRepository.findById(maDonHang).orElse(null);
    }
    public DonHang capNhatDonHang(DonHang donHang) {
        donHangRepository.save(donHang);
        return donHang;
    }



}