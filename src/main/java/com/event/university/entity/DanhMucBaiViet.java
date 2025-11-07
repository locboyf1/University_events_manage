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
@Table(name = "danhmucbaiviet")
public class DanhMucBaiViet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Tên danh mục không được để trống")
	@Size(max = 30, message = "Tên danh mục không được quá dài")
	@Column(name = "tendanhmuc", length = 30, nullable = false)
	private String tenDanhMuc;

	@Size(max = 500, message = "Mô tả không được quá dài")
	@Column(name = "mota", length = 500)
	private String moTa;

	@Column(name = "thutu", nullable = false, unique = true)
	private Integer thuTu;

	@OneToMany(mappedBy = "danhMucBaiViet")
	private Set<BaiViet> baiViet = new HashSet<>();

	public DanhMucBaiViet() {
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