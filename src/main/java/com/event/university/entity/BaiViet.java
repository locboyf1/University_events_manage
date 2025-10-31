package com.event.university.entity;

import java.time.LocalDateTime;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "baiviet")
public class BaiViet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(max = 200, message = "Tiêu đề không được quá dài")
	@NotBlank(message = "Tiêu đề không được để trống")
	@Column(name = "tieude", length = 200, nullable = false)
	private String tieuDe;

	@Column(name = "bidanh", length = 200, nullable = false)
	private String biDanh;

	@Size(max = 500, message = "Mô tả không được quá dài")
	@NotBlank(message = "Mô tả không  được để trống")
	@Column(name = "mota", length = 500)
	private String moTa;

	@NotBlank(message = "Nội dung không được để trống")
	@Column(name = "noidung", columnDefinition = "LONGTEXT", nullable = false)
	private String noiDung;

	@Column(name = "ngaytao", nullable = false)
	private LocalDateTime ngayTao;

	@Column(name = "hienthi", nullable = false)
	private boolean hienThi;

	@Column(name = "anh", columnDefinition = "LONGTEXT", nullable = false)
	private String anh;

	@ManyToOne
	@JoinColumn(name = "manguoidung", nullable = false)
	private NguoiDung nguoiDung;

	@ManyToOne
	@JoinColumn(name = "madanhmuc", nullable = false)
	private DanhMucBaiViet danhMucBaiViet;

	@OneToMany(mappedBy = "baiViet")
	private Set<BinhLuanBaiViet> binhLuan = new HashSet<>();

	public BaiViet() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public LocalDateTime getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDateTime ngayTao) {
		this.ngayTao = ngayTao;
	}

	public boolean getHienThi() {
		return hienThi;
	}

	public void setHienThi(boolean hienThi) {
		this.hienThi = hienThi;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public NguoiDung getNguoiDung() {
		return nguoiDung;
	}

	public void setNguoiDung(NguoiDung nguoiDung) {
		this.nguoiDung = nguoiDung;
	}

	public DanhMucBaiViet getDanhMucBaiViet() {
		return danhMucBaiViet;
	}

	public void setDanhMucBaiViet(DanhMucBaiViet danhMucBaiViet) {
		this.danhMucBaiViet = danhMucBaiViet;
	}

	public Set<BinhLuanBaiViet> getBinhLuan() {
		return binhLuan;
	}

	public void setBinhLuan(Set<BinhLuanBaiViet> binhLuan) {
		this.binhLuan = binhLuan;
	}

	public void setBiDanh(String biDanh) {
		this.biDanh = biDanh;
	}

	public String getBiDanh() {
		return this.biDanh;
	}
}