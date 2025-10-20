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
@Table(name = "khoa")
public class Khoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tenkhoa", length = 100, nullable = false)
	private String tenKhoa;

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