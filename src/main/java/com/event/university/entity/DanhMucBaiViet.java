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
@Table(name = "danhmucbaiviet")
public class DanhMucBaiViet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tendanhmuc", length = 30, nullable = false)
	private String tenDanhMuc;

	@Column(name = "mota", length = 500)
	private String moTa;

	@Column(name = "thutu", nullable = false)
	private Integer thuTu;

	@OneToMany(mappedBy = "danhMucBaiViet")
	private Set<BaiViet> baiViet = new HashSet<>();

	public DanhMucBaiViet() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
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

	public void setThuTu(Integer thuTu) {
		this.thuTu = thuTu;
	}

	public Set<BaiViet> getBaiViet() {
		return baiViet;
	}

	public void setBaiViet(Set<BaiViet> baiViet) {
		this.baiViet = baiViet;
	}
}