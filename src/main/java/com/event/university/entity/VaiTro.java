package com.event.university.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="vaitro")
public class VaiTro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "tenvaitro", length = 30, nullable = false)
    private String tenVaiTro;

    @Column(name = "mota", length = 100)
    private String moTa;

    @Column(name = "bidanh", length = 20)
    private String biDanh;

    @Column(name = "quyentaosukien")
    private boolean quyenTaoSuKien;

    @Column(name = "quyenduyetsukien")
    private boolean quyenDuyetSuKien;

    @Column(name = "quyenquanlybaiviet")
    private boolean quyenQuanLyBaiViet;

    @Column(name = "quyensuamenu")
    private boolean quyenSuaMenu;

    @Column(name = "quyenquanlynguoidung")
    private boolean quyenQuanLyNguoiDung;
    
    @Column(name = "quyenthemsukien")
    private boolean quyenThemSuKien;

    @Column(name = "quyenhientrollenlac")
    private boolean quyenHienThiRoleLenLac;

    public VaiTro() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getBiDanh() {
        return biDanh;
    }

    public void setBiDanh(String biDanh) {
        this.biDanh = biDanh;
    }

    public boolean isQuyenTaoSuKien() {
        return quyenTaoSuKien;
    }

    public void setQuyenTaoSuKien(boolean quyenTaoSuKien) {
        this.quyenTaoSuKien = quyenTaoSuKien;
    }

    public boolean isQuyenDuyetSuKien() {
        return quyenDuyetSuKien;
    }

    public void setQuyenDuyetSuKien(boolean quyenDuyetSuKien) {
        this.quyenDuyetSuKien = quyenDuyetSuKien;
    }

    public boolean isQuyenQuanLyBaiViet() {
        return quyenQuanLyBaiViet;
    }

    public void setQuyenQuanLyBaiViet(boolean quyenQuanLyBaiViet) {
        this.quyenQuanLyBaiViet = quyenQuanLyBaiViet;
    }

    public boolean isQuyenSuaMenu() {
        return quyenSuaMenu;
    }

    public void setQuyenSuaMenu(boolean quyenSuaMenu) {
        this.quyenSuaMenu = quyenSuaMenu;
    }

    public boolean isQuyenQuanLyNguoiDung() {
        return quyenQuanLyNguoiDung;
    }

    public void setQuyenQuanLyNguoiDung(boolean quyenQuanLyNguoiDung) {
        this.quyenQuanLyNguoiDung = quyenQuanLyNguoiDung;
    }
    
    public boolean isQuyenThemSuKien() {
        return quyenThemSuKien;
    }

    public void setQuyenThemSuKien(boolean quyenThemSuKien) {
        this.quyenThemSuKien = quyenThemSuKien;
    }

    public boolean isQuyenHienThiRoleLenLac() {
        return quyenHienThiRoleLenLac;
    }

    public void setQuyenHienThiRoleLenLac(boolean quyenHienThiRoleLenLac) {
        this.quyenHienThiRoleLenLac = quyenHienThiRoleLenLac;
    }
    
    @OneToMany(mappedBy = "vaiTro")
    private Set<NguoiDung> nguoiDung = new HashSet<>();

    // Getters and Setters for events
    public Set<NguoiDung> getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(Set<NguoiDung> nguoiDung) {
        this.nguoiDung = nguoiDung;
    }
}
