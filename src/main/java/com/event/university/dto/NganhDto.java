package com.event.university.dto;

import java.util.Set;

public class NganhDto {
	private Integer id;
	private String tenNganh;
	private String moTa;
	private Set<LopDto> lop;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Set<LopDto> getLop() {
		return lop;
	}

	public void setLop(Set<LopDto> lop) {
		this.lop = lop;
	}
}