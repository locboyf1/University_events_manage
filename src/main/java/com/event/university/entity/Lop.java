package com.event.university.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "lop")
public class Lop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tenlop", length = 100, nullable = false)
	private String tenLop;

	@Column(name = "khoaso", nullable = false)
	private Integer khoaSo;

	@Column(name = "mota", length = 500)
	private String moTa;

	@ManyToOne
	@JoinColumn(name = "manganh", nullable = false)
	@JsonIgnore
	private Nganh nganh;

	@OneToMany(mappedBy = "lop")
	private Set<NguoiDung> nguoiDung = new HashSet<>();

	public Lop() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public Integer getKhoaSo() {
		return khoaSo;
	}

	public void setKhoaSo(Integer khoaSo) {
		this.khoaSo = khoaSo;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public Nganh getNganh() {
		return nganh;
	}

	public void setNganh(Nganh nganh) {
		this.nganh = nganh;
	}

	public Set<NguoiDung> getNguoiDung() {
		return nguoiDung;
	}

	public void setNguoiDung(Set<NguoiDung> nguoiDung) {
		this.nguoiDung = nguoiDung;
	}
}