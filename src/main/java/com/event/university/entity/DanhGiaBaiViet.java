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

@Entity
@Table(name = "danhgiabaiviet")
public class DanhGiaBaiViet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
	@Column(name = "thoigian", nullable = false)
	private LocalDateTime thoiGian;
	
    @ManyToOne
    @JoinColumn(name = "manguoidung")
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "mabaiviet")
    private BaiViet baiViet;
    
    public DanhGiaBaiViet() {
	}
    @Column(name = "sosao")
    private Float soSao;

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
	public Float getSoSao() {
	    return soSao;
	}
	public void setSoSao(Float soSao) {
	    this.soSao = soSao;
	}

}

