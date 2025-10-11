package com.event.university.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "baiviet")
public class BaiViet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tieude", length = 200, nullable = false)
    private String tieuDe;

    @Column(name = "mota", length = 500)
    private String moTa;

    @Column(name = "noidung", columnDefinition = "LONGTEXT", nullable = false)
    private String noiDung;

    @Column(name = "ngaytao", nullable = false)
    private LocalDateTime ngayTao;

    @Column(name = "hienthi", nullable = false)
    private boolean hienThi;

    @Column(name = "anh", columnDefinition = "LONGTEXT", nullable = false)
    private String anh;

    @ManyToOne
    @JoinColumn(name = "manguoidung", nullable = false)
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "madanhmuc", nullable = false)
    private DanhMucBaiViet danhMucBaiViet;
    
    @OneToMany(mappedBy = "baiViet")
    private Set<BinhLuanBaiViet> binhLuan = new HashSet<>();

    public BaiViet() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTieuDe() { return tieuDe; }
    public void setTieuDe(String tieuDe) { this.tieuDe = tieuDe; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }
    public LocalDateTime getNgayTao() { return ngayTao; }
    public void setNgayTao(LocalDateTime ngayTao) { this.ngayTao = ngayTao; }
    public boolean isHienThi() { return hienThi; }
    public void setHienThi(boolean hienThi) { this.hienThi = hienThi; }
    public String getAnh() { return anh; }
    public void setAnh(String anh) { this.anh = anh; }
    public NguoiDung getNguoiDung() { return nguoiDung; }
    public void setNguoiDung(NguoiDung nguoiDung) { this.nguoiDung = nguoiDung; }
    public DanhMucBaiViet getDanhMucBaiViet() { return danhMucBaiViet; }
    public void setDanhMucBaiViet(DanhMucBaiViet danhMucBaiViet) { this.danhMucBaiViet = danhMucBaiViet; }
    public Set<BinhLuanBaiViet> getBinhLuan() { return binhLuan; }
    public void setBinhLuan(Set<BinhLuanBaiViet> binhLuan) { this.binhLuan = binhLuan; }
}