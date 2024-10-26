package org.example.quanlydonhangs.Model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "loaisanpham")
public class LoaiSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maLoaiSp;

    @Column(name = "ten_loai_sp", nullable = false)
    private String tenLoaiSp;


    @OneToMany(mappedBy = "loaiSanPham", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SanPham> sanPham;


    public Long getMaLoaiSp() {
        return maLoaiSp;
    }

    public void setMaLoaiSp(Long maLoaiSp) {
        this.maLoaiSp = maLoaiSp;
    }

    public String getTenLoaiSp() {
        return tenLoaiSp;
    }

    public void setTenLoaiSp(String tenLoaiSp) {
        this.tenLoaiSp = tenLoaiSp;
    }

    public List<SanPham> getSanPham() {
        return sanPham;
    }

    public void setSanPhams(List<SanPham> sanPham) {
        this.sanPham = sanPham;
    }
}
