package com.event.university.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "nguoidung")
public class NguoiDung {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "taikhoan", length = 30, nullable = false)
	private String taiKhoan;

	@Column(name = "matkhau", length = 200, nullable = false)
	private String matKhau;

	@Column(name = "hoten", length = 60, nullable = false)
	private String hoTen;

	@Column(name = "sdt", length = 11, nullable = true)
	private String sDT;

	@Column(name = "email", length = 50, nullable = true)
	private String email;

	@Column(name = "anh", nullable = false)
	private String anh;

	@Column(name = "hoatdong", nullable = false)
	private boolean hoatDong;

	public NguoiDung() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSDT() {
		return sDT;
	}

	public void setSDT(String sDT) {
		this.sDT = sDT;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public boolean getHoatDong() {
		return hoatDong;
	}

	public void setHoatDong(boolean hoatDong) {
		this.hoatDong = hoatDong;
	}

	@ManyToOne
	@JoinColumn(name = "mavaitro")
	private VaiTro vaiTro;

	public VaiTro getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(VaiTro vaiTro) {
		this.vaiTro = vaiTro;
	}

	@ManyToOne
	@JoinColumn(name = "malop", nullable = true)
	private Lop lop;

	public Lop getLop() {
		return lop;
	}

	public void setLop(Lop lop) {
		this.lop = lop;
	}
}
