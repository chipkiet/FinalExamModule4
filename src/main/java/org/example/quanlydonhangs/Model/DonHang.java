package org.example.quanlydonhangs.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "donhang")
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDonHang;

    @Column(name = "ngay_mua", nullable = false)
    private LocalDateTime ngayMua;

    @Column(name = "so_luong", nullable = false)
    private int soLuong;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_sp", nullable = false)
    private SanPham sanPham;


    public Long getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(Long maDonHang) {
        this.maDonHang = maDonHang;
    }

    public LocalDateTime getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(LocalDateTime ngayMua) {
        this.ngayMua = ngayMua;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }


    public double getTongTien() {
        return this.sanPham.getGiaSp() * this.soLuong;
    }
}