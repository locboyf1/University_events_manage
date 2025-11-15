package com.event.university.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DanhGiaSuKien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "sosao", nullable = false)
	private Integer soSao;

	@Column(name = "noidung", nullable = false)
	private String noiDung;

	@ManyToOne()
	@JoinColumn(name = "masukien", nullable = false)
	private SuKien suKien;

	@ManyToOne()
	@JoinColumn(name = "manguoidung", nullable = false)
	private NguoiDung nguoiDung;

	public Integer getId() {
		return id;
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

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
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
}
