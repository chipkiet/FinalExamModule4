package org.example.quanlydonhangs.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sanpham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maSp;

    @Column(name = "ten_sp", nullable = false)
    private String tenSp;

    @Column(name = "gia_sp", nullable = false)
    private Double giaSp;

    @Column(name = "tinh_trang_sp")
    private String tinhTrangSp;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_loai_sp", nullable = false)
    private LoaiSanPham loaiSanPham;


    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DonHang> donHang;


    public Long getMaSp() {
        return maSp;
    }

    public void setMaSp(Long maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public Double getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(Double giaSp) {
        this.giaSp = giaSp;
    }

    public String getTinhTrangSp() {
        return tinhTrangSp;
    }

    public void setTinhTrangSp(String tinhTrangSp) {
        this.tinhTrangSp = tinhTrangSp;
    }

    public LoaiSanPham getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public List<DonHang> getDonHang() {
        return donHang;
    }

    public void setDonHangs(List<DonHang> donHang) {
        this.donHang = donHang;
    }
}