package org.example.quanlydonhangs.Controller;

import org.example.quanlydonhangs.Model.DonHang;
import org.example.quanlydonhangs.Service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDate;

import java.util.List;

@Controller
public class DonHangController {

    @Autowired
    private DonHangService donHangService;

    @GetMapping("/orders")
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

    @GetMapping("/orders/edit")
    public String hienThiFormSuaDonHang(@RequestParam("maDonHang") Long maDonHang, Model model) {
        DonHang order = donHangService.layDonHangTheoId(maDonHang);
        if (order != null) {
            model.addAttribute("order", order);
        } else {
            model.addAttribute("error", "Không tìm thấy đơn hàng.");
        }
        return "editDonHang";
    }
    @PostMapping("/orders/update")
    public String capNhatDonHang(@ModelAttribute("order") DonHang donHang,
                                 RedirectAttributes redirectAttributes) {
        try {
            DonHang updatedOrder = donHangService.capNhatDonHang(donHang);
            redirectAttributes.addFlashAttribute("message", "Cập nhật đơn hàng thành công!");
            return "redirect:/orders";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi cập nhật đơn hàng: " + e.getMessage());
            return "redirect:/orders/edit?maDonHang=" + donHang.getMaDonHang();
        }
    }
}