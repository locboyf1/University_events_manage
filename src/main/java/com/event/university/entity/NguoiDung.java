package com.event.university.entity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "nguoidung")
public class NguoiDung implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Tài khoản không được để trống")
	@Size(max = 30, message = "Tài khoản không được quá 30 ký tự")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Tên đăng nhập không được chứa dấu cách hoặc ký tự đặc biệt.")
	@Column(name = "taikhoan", length = 30, nullable = false)
	private String taiKhoan;

	@NotBlank(message = "Mật khẩu không được để trống")
	@Column(name = "matkhau", length = 200, nullable = false)
	private String matKhau;

	@Column(name = "hoten", length = 60, nullable = false)
	private String hoTen;

	@Column(name = "sdt", length = 11, nullable = true)
	private String sDT;

	@Column(name = "email", length = 50, nullable = true)
	private String email;

	@Lob
	@Column(name = "anh", nullable = true, columnDefinition = "LONGTEXT")
	private String anh;

	@Column(name = "hoatdong", nullable = false)
	private boolean hoatDong;

	@ManyToOne
	@JoinColumn(name = "malop", nullable = true)
	private Lop lop;

	@ManyToOne
	@JoinColumn(name = "mavaitro")
	private VaiTro vaiTro;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.vaiTro.getDsQuyen().stream().map(q -> new SimpleGrantedAuthority(q.getTenQuyen())).collect(Collectors.toList());
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this;
	}

	public Boolean isCoQuyen(String tenQuyen) {
		Boolean coQuyen = this.vaiTro.getDsQuyen().stream().anyMatch(q -> q.getTenQuyen().equals(tenQuyen));
		return coQuyen;
	}

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
		if (this.anh == null) {
			return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMdBqlgrpFx60XH_CdP3DpEZ7oTmvQuF4i9A&s";
		}
		return this.anh;
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

	public VaiTro getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(VaiTro vaiTro) {
		this.vaiTro = vaiTro;
	}

	public Lop getLop() {
		return lop;
	}

	public void setLop(Lop lop) {
		this.lop = lop;
	}

	@Override
	public String getPassword() {
		return this.matKhau;
	}

	@Override
	public String getUsername() {
		return this.taiKhoan;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.hoatDong;
	}
}
