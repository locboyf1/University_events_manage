package com.event.university.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tieude", nullable = false, length = 30)
    private String tieuDe;

    @Column(name = "mota", length = 100)
    private String moTa;

    @Column(name = "thutu", nullable = false)
    private Integer thuTu;

    @Column(name = "hien", nullable = false)
    private Boolean hien;

    @Column(name = "bidanh", nullable = false, length = 30)
    private String biDanh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mamenucha")
    private Menu menuCha;

    @OneToMany(mappedBy = "menuCha", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Menu> menuCon;

    public Menu() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getThuTu() {
        return thuTu;
    }

    public void setThutu(Integer thuTu) {
        this.thuTu = thuTu;
    }

    public Boolean getHien() {
        return hien;
    }

    public void setHien(Boolean hien) {
        this.hien = hien;
    }

    public String getBiDanh() {
        return biDanh;
    }

    public void setBiDanh(String biDanh) {
        this.biDanh = biDanh;
    }

    public Menu getMenuCha() {
        return menuCha;
    }

    public void setMenuCha(Menu menuCha) {
        this.menuCha = menuCha;
    }

    public Set<Menu> getMenuCon() {
        return menuCon;
    }

    public void setMenuCon(Set<Menu> menuCon) {
        this.menuCon = menuCon;
    }
}