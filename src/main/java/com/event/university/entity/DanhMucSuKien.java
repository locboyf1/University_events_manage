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
@Table(name = "danhmucsukien")
public class DanhMucSuKien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tendanhmuc", length = 30, nullable = false)
	private String tenDanhMuc;

	@Column(name = "thutu", nullable = false)
	private Integer thuTu;

	@Column(name = "mota", length = 500)
	private String moTa;

	@Column(name = "bidanh", length = 20)
	private String biDanh;

	@OneToMany(mappedBy = "danhMucSuKien")
	private Set<SuKien> suKien = new HashSet<>();

	public DanhMucSuKien() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}

	public Integer getThuTu() {
		return thuTu;
	}

	public void setThuTu(Integer thuTu) {
		this.thuTu = thuTu;
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

	public Set<SuKien> getSuKien() {
		return suKien;
	}

	public void setSuKien(Set<SuKien> suKien) {
		this.suKien = suKien;
	}
}