package com.event.university.dto;

public class LopDto {
	private Long id;
	private String tenLop;
	private Integer khoaSo;

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
}