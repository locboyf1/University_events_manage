package com.event.university.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "nganh")
public class Nganh {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tennganh", length = 100, nullable = false)
	private String tenNganh;

	@Column(name = "mota", length = 500)
	private String moTa;

	@ManyToOne
	@JoinColumn(name = "makhoa", nullable = false)
	private Khoa khoa;

	@OneToMany(mappedBy = "nganh")
	private Set<Lop> lop = new HashSet<>();

	public Nganh() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenNganh() {
		return tenNganh;
	}

	public void setTenNganh(String tenNganh) {
		this.tenNganh = tenNganh;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public Khoa getKhoa() {
		return khoa;
	}

	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}

	public Set<Lop> getLop() {
		return lop;
	}

	public void setLop(Set<Lop> lop) {
		this.lop = lop;
	}
}