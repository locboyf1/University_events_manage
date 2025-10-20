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
@Table(name = "sukien")
public class SuKien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tensukien", length = 300, nullable = false)
	private String tenSuKien;

	@Column(name = "mota", length = 500)
	private String moTa;

	@Column(name = "noidung", columnDefinition = "LONGTEXT", nullable = false)
	private String noiDung;

	@Column(name = "anh", length = 500, nullable = false)
	private String anh;

	@Column(name = "thoigiantao", nullable = false)
	private LocalDateTime thoiGianTao;

	@Column(name = "thoigianbatdau", nullable = false)
	private LocalDateTime thoiGianBatDau;

	@Column(name = "thoigianketthuc")
	private LocalDateTime thoiGianKetThuc;

	@Column(name = "thoigiansuagannhat")
	private LocalDateTime thoiGianSuaGanNhat;

	@Column(name = "batbuoc", nullable = false)
	private boolean batBuoc;

	@Column(name = "hienthi", nullable = false)
	private boolean hienThi;

	@Column(name = "duyet", nullable = false)
	private boolean duyet;

	@Column(name = "diachi", length = 100, nullable = false)
	private String diaChi;

	@Column(name = "bidanh", length = 300, nullable = false)
	private String biDanh;

	@Column(name = "sdthotro", length = 11, nullable = false)
	private String sdtHoTro;

	@Column(name = "emailhotro", length = 50, nullable = false)
	private String emailHoTro;

	@ManyToOne
	@JoinColumn(name = "manguoidung", nullable = false)
	private NguoiDung nguoiDung;

	@ManyToOne
	@JoinColumn(name = "madanhmuc", nullable = false)
	private DanhMucSuKien danhMucSuKien;

	@OneToMany(mappedBy = "suKien")
	private Set<DanhSachThamGia> danhSachThamGia = new HashSet<>();

	public SuKien() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSdtHoTro() {
		return sdtHoTro;
	}

	public void setSdtHoTro(String sdtHoTro) {
		this.sdtHoTro = sdtHoTro;
	}

	public String getEmailHoTro() {
		return emailHoTro;
	}

	public void setEmailHoTro(String emailHoTro) {
		this.emailHoTro = emailHoTro;
	}

	public String getTenSuKien() {
		return tenSuKien;
	}

	public void setTenSuKien(String tenSuKien) {
		this.tenSuKien = tenSuKien;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public String getBiDanh() {
		return biDanh;
	}

	public void setBiDanh(String biDanh) {
		this.biDanh = biDanh;
	}

	public LocalDateTime getThoiGianTao() {
		return thoiGianTao;
	}

	public void setThoiGianTao(LocalDateTime thoiGianTao) {
		this.thoiGianTao = thoiGianTao;
	}

	public LocalDateTime getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}

	public LocalDateTime getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public LocalDateTime getThoiGianSuaGanNhat() {
		return thoiGianSuaGanNhat;
	}

	public void setThoiGianSuaGanNhat(LocalDateTime thoiGianSuaGanNhat) {
		this.thoiGianSuaGanNhat = thoiGianSuaGanNhat;
	}

	public boolean isBatBuoc() {
		return batBuoc;
	}

	public void setBatBuoc(boolean batBuoc) {
		this.batBuoc = batBuoc;
	}

	public boolean isHienThi() {
		return hienThi;
	}

	public void setHienThi(boolean hienThi) {
		this.hienThi = hienThi;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public NguoiDung getNguoiDung() {
		return nguoiDung;
	}

	public void setNguoiDung(NguoiDung nguoiDung) {
		this.nguoiDung = nguoiDung;
	}

	public DanhMucSuKien getDanhMucSuKien() {
		return danhMucSuKien;
	}

	public void setDanhMucSuKien(DanhMucSuKien danhMucSuKien) {
		this.danhMucSuKien = danhMucSuKien;
	}

	public Set<DanhSachThamGia> getDanhSachThamGia() {
		return danhSachThamGia;
	}

	public void setDanhSachThamGia(Set<DanhSachThamGia> danhSachThamGia) {
		this.danhSachThamGia = danhSachThamGia;
	}

}