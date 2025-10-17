package com.event.university.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "quyen")
public class Quyen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tenquyen", length = 50, nullable = false)
	private String tenQuyen;

	@Column(name = "mota", length = 50)
	private String moTa;

	@ManyToMany(mappedBy = "dsQuyen")
	private Set<VaiTro> dsVaiTro;

	// Constructors, Getters and Setters
	public Quyen() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenQuyen() {
		return tenQuyen;
	}

	public void setTenQuyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public Set<VaiTro> getDsVaiTro() {
		return dsVaiTro;
	}

	public void setDsVaiTro(Set<VaiTro> dsVaiTro) {
		this.dsVaiTro = dsVaiTro;
	}
}