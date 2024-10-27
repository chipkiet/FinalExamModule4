package org.example.quanlydonhangs.Service;

import org.example.quanlydonhangs.Model.DonHang;
import org.example.quanlydonhangs.Repository.DonHangRepository;
import org.example.quanlydonhangs.Repository.LoaiSanPhamRepository;
import org.example.quanlydonhangs.Repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DonHangService {

    @Autowired
    private DonHangRepository donHangRepository;

    @Autowired
    private LoaiSanPhamRepository loaiSanPhamRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

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
    public DonHang layDonHangTheoId(Long maDonHang) {
        return donHangRepository.findById(maDonHang).orElse(null);
    }
    public DonHang capNhatDonHang(DonHang donHang) {
        donHangRepository.save(donHang);
        return donHang;
    }
}