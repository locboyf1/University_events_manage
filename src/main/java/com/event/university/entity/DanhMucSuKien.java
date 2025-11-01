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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "danhmucsukien")
public class DanhMucSuKien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(max = 30, message = "Tên danh mục không được quá dài")
	@NotBlank(message = "Tên danh mục không được để trống")
	@Column(name = "tendanhmuc", length = 30, nullable = false)
	private String tenDanhMuc;

	@Column(name = "thutu", nullable = false, unique = true)
	private Integer thuTu;

	@Size(max = 500, message = "Mô tả không được quá dài")
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