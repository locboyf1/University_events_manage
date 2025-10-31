package com.event.university.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class NguoiDungDto {

	@NotBlank(message = "Tài khoản không được để trống")
	@Size(max = 30, message = "Tài khoản không được quá 30 ký tự")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Tài khoản không được chứa dấu cách hoặc ký tự đặc biệt")
	private String taiKhoan;

	@Pattern(regexp = "^[^\s]*$", message = "Mật khẩu không được chứa dấu cách")
	@Size(min = 8, max = 16, message = "Mật khẩu ngắn nhất là 8 và dài nhất là 16 ký tự")
	private String matKhau;

	@NotBlank(message = "Họ tên không được để trống")
	@Size(max = 60, message = "Vui lòng nhập đúng họ tên")
	private String hoTen;

	private boolean hoatDong;

	private Integer maLop;

	private Integer maVaiTro;

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

	public boolean getHoatDong() {
		return hoatDong;
	}

	public void setHoatDong(boolean hoatDong) {
		this.hoatDong = hoatDong;
	}

	public Integer getMaLop() {
		return maLop;
	}

	public void setMaLop(Integer maLop) {
		this.maLop = maLop;
	}

	public Integer getMaVaiTro() {
		return maVaiTro;
	}

	public void setMaVaiTro(Integer maVaiTro) {
		this.maVaiTro = maVaiTro;
	}
}