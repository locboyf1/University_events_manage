package com.event.university.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "danhsachthamgia")
public class DanhSachThamGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "masukien", nullable = false)
    private SuKien suKien;

    @ManyToOne
    @JoinColumn(name = "manguoidung", nullable = false)
    private NguoiDung nguoiDung;
    
    private String trangThai;

    public DanhSachThamGia() {}

    public DanhSachThamGia(SuKien suKien, NguoiDung nguoiDung) {
        this.suKien = suKien;
        this.nguoiDung = nguoiDung;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SuKien getSuKien() {
        return suKien;
    }

    public void setSuKien(SuKien suKien) {
        this.suKien = suKien;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}