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
@Table(name = "khoa")
public class Khoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Không được để trống tên khoa")
	@Size(max = 50, message = "Tên khoa không được quá dài")
	@Column(name = "tenkhoa", length = 50, nullable = false)
	private String tenKhoa;

	@Size(max = 500, message = "Mô tả không được quá dài")
	@Column(name = "mota", length = 500)
	private String moTa;

	@OneToMany(mappedBy = "khoa")
	private Set<Nganh> nganh = new HashSet<>();

	public Khoa() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenKhoa() {
		return tenKhoa;
	}

	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public Set<Nganh> getNganh() {
		return nganh;
	}

	public void setNganh(Set<Nganh> nganh) {
		this.nganh = nganh;
	}
}