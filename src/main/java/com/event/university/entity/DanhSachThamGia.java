package com.event.university.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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

	@Column(name = "thoigianthamgia", nullable = false)
	private LocalDateTime thoiGianThamGia;

	@Column(name = "thoigiandiemdanh", nullable = true)
	private LocalDateTime thoiGianDiemDanh;

	@Column(name = "anhdiemdanh", columnDefinition = "LONGTEXT", nullable = true)
	private String anhDiemDanh;

	public DanhSachThamGia() {
	}

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

	public LocalDateTime getThoiGianThamGia() {
		return thoiGianThamGia;
	}

	public void setThoiGianThamGia(LocalDateTime thoiGianThamGia) {
		this.thoiGianThamGia = thoiGianThamGia;
	}

	public LocalDateTime getThoiGianDiemDanh() {
		return thoiGianDiemDanh;
	}

	public void setThoiGianDiemDanh(LocalDateTime thoiGianDiemDanh) {
		this.thoiGianDiemDanh = thoiGianDiemDanh;
	}

	public String getAnhDiemDanh() {
		return anhDiemDanh;
	}

	public void setAnhDiemDanh(String anhDiemDanh) {
		this.anhDiemDanh = anhDiemDanh;
	}

	public Boolean getDaDiemDanh() {
		return (this.thoiGianDiemDanh != null && this.anhDiemDanh != null);
	}

}