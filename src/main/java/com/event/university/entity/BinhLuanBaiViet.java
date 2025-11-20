package com.event.university.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "binhluanbaiviet")
public class BinhLuanBaiViet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "thoigian", nullable = false)
	private LocalDateTime thoiGian;

	@NotBlank(message = "Vui lòng nhập nội dung bình luận")
	@Size(max = 500, message = "Nội dung bình luận khộng được quá dài")
	@Column(name = "noidung", length = 500, nullable = false)
	private String noiDung;

	@ManyToOne
	@JoinColumn(name = "manguoidung", nullable = false)
	private NguoiDung nguoiDung;

	@ManyToOne()
	@JoinColumn(name = "mabaiviet", nullable = false)
	private BaiViet baiViet;

	public BinhLuanBaiViet() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(LocalDateTime thoiGian) {
		this.thoiGian = thoiGian;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public NguoiDung getNguoiDung() {
		return nguoiDung;
	}

	public void setNguoiDung(NguoiDung nguoiDung) {
		this.nguoiDung = nguoiDung;
	}

	public BaiViet getBaiViet() {
		return baiViet;
	}

	public void setBaiViet(BaiViet baiViet) {
		this.baiViet = baiViet;
	}
}