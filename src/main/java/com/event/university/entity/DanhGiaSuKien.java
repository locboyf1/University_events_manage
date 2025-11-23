package com.event.university.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "danhgiasukien")
public class DanhGiaSuKien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "sosao", nullable = false)
	private Integer soSao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "masukien", nullable = false)
	private SuKien suKien;

	@Size(max = 500, message = "Đánh giá không được quá dài")
	@Column(name = "noidung")
	private String noiDung;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manguoidung", nullable = false)
	private NguoiDung nguoiDung;

	@Column(name = "thoigian", nullable = false)
	private LocalDateTime thoiGian;

	public DanhGiaSuKien() {
	}

	public Integer getId() {
		return id;
	}

	public LocalDateTime getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(LocalDateTime thoiGian) {
		this.thoiGian = thoiGian;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSoSao() {
		return soSao;
	}

	public void setSoSao(Integer soSao) {
		this.soSao = soSao;
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

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getNoiDung() {
		return this.noiDung;
	}
}
