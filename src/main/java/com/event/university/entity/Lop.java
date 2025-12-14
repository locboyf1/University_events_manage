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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "lop")
public class Lop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Size(max = 50, message = "Tên lớp không được quá dài")
	@NotBlank(message = "Tên lớp không được để trống")
	@Column(name = "tenlop", length = 50, nullable = false)
	private String tenLop;

	@NotNull(message = "Khóa không được để trống")
	@Positive(message = "Khóa phải là số nguyên dương")
	@Column(name = "khoaso", nullable = false)
	private Integer khoaSo;

	@Size(max = 500, message = "Mô tả không được quá dài")
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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