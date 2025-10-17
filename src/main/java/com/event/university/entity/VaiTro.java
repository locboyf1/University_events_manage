package com.event.university.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vaitro")
public class VaiTro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tenvaitro", length = 30)
	private String tenVaiTro;

	@Column(name = "mota", length = 100)
	private String moTa;

	@Column(name = "bidanh", length = 20, nullable = false)
	private String biDanh;

	@Column(name = "capbac")
	private Integer capBac;

	@OneToMany(mappedBy = "vaiTro")
	private Set<NguoiDung> dsNguoiDung;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "chitietquyen", joinColumns = @JoinColumn(name = "mavaitro"), inverseJoinColumns = @JoinColumn(name = "maquyen"))
	private Set<Quyen> dsQuyen;

	// Constructors, Getters and Setters
	public VaiTro() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenVaiTro() {
		return tenVaiTro;
	}

	public void setTenVaiTro(String tenVaiTro) {
		this.tenVaiTro = tenVaiTro;
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

	public Integer getCapBac() {
		return capBac;
	}

	public void setCapBac(Integer capBac) {
		this.capBac = capBac;
	}

	public Set<NguoiDung> getDsNguoiDung() {
		return dsNguoiDung;
	}

	public void setDsNguoiDung(Set<NguoiDung> dsNguoiDung) {
		this.dsNguoiDung = dsNguoiDung;
	}

	public Set<Quyen> getDsQuyen() {
		return dsQuyen;
	}

	public void setDsQuyen(Set<Quyen> dsQuyen) {
		this.dsQuyen = dsQuyen;
	}
}