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
@Table(name = "lienlac")
public class LienLac {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ten", length = 50, nullable = false)
	private String ten;

	@Column(name = "email", length = 30, nullable = false)
	private String email;

	@Column(name = "sdt", length = 11, nullable = false)
	private String sdt;

	@Column(name = "tieude", length = 100, nullable = false)
	private String tieuDe;

	@Column(name = "noidung", length = 500, nullable = false)
	private String noiDung;

	@Column(name = "thoigian", nullable = false)
	private LocalDateTime thoiGian;

	@Column(name = "daphanhoi", nullable = false)
	private boolean daPhanHoi;

	@ManyToOne
	@JoinColumn(name = "manguoidung")
	private NguoiDung nguoiDung;

	public LienLac() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public LocalDateTime getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(LocalDateTime thoiGian) {
		this.thoiGian = thoiGian;
	}

	public boolean isDaPhanHoi() {
		return daPhanHoi;
	}

	public void setDaPhanHoi(boolean daPhanHoi) {
		this.daPhanHoi = daPhanHoi;
	}

	public NguoiDung getNguoiDung() {
		return nguoiDung;
	}

	public void setNguoiDung(NguoiDung nguoiDung) {
		this.nguoiDung = nguoiDung;
	}
}