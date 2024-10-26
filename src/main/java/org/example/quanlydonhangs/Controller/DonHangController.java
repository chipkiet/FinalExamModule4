package org.example.quanlydonhangs.Controller;

import org.example.quanlydonhangs.Model.DonHang;
import org.example.quanlydonhangs.Service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;

import java.util.List;

@Controller
public class DonHangController {

    @Autowired
    private DonHangService donHangService;

    @GetMapping("/donhang")
    public String hienThiDonHang(Model model) {
        List<DonHang> orders = donHangService.layTatCaDonHang();
        model.addAttribute("orders", orders);
        return "donhang";
    }

    @GetMapping("/orders/filter")
    public String locDonHangTheoNgay(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model
    ) {
        try {
            List<DonHang> orders = donHangService.layDonHangTheoNgay(startDate, endDate);
            model.addAttribute("orders", orders);
        } catch (Exception e) {
            model.addAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        return "donhang";
    }

    @GetMapping("/orders/top")
    public String hienThiTopDonHang(
            @RequestParam("topCount") int topCount,
            Model model
    ) {
        try {
            List<DonHang> topOrders = donHangService.layTopDonHang(topCount);
            model.addAttribute("orders", topOrders);
        } catch (Exception e) {
            model.addAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        return "donhang";
    }
}