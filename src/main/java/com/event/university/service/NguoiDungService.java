package com.event.university.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.event.university.entity.NguoiDung;
import com.event.university.repository.NguoiDungRepository;

@Service
public class NguoiDungService implements UserDetailsService {

	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<NguoiDung> getAll() {
		return nguoiDungRepository.findAll();

	}

	public NguoiDung getById(Long id) {
		return nguoiDungRepository.findById(id).orElse(null);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		NguoiDung nguoiDung = nguoiDungRepository.findByTaiKhoan(username).orElseThrow(
				() -> new UsernameNotFoundException("Không tìm thấy người dùng với tài khoản: " + username));

		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(nguoiDung.getVaiTro().getBiDanh()));

		return new User(nguoiDung.getTaiKhoan(), nguoiDung.getMatKhau(), nguoiDung.getHoatDong(), true, true, true,
				authorities);
	}

	public void update(NguoiDung nguoiDungUpdated) {
		NguoiDung nguoiDungPreviou = nguoiDungRepository.findById(nguoiDungUpdated.getId()).orElse(null);

		if (nguoiDungPreviou != null) {
			nguoiDungPreviou.setTaiKhoan(nguoiDungUpdated.getTaiKhoan());
			nguoiDungPreviou.setHoTen(nguoiDungUpdated.getHoTen());
			nguoiDungPreviou.setVaiTro(nguoiDungUpdated.getVaiTro());
			nguoiDungPreviou.setLop(nguoiDungUpdated.getLop());
			nguoiDungRepository.save(nguoiDungPreviou);
		}
	}

	public void save(NguoiDung nguoiDung) {

		String rawPassword = nguoiDung.getMatKhau();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		nguoiDung.setMatKhau(encodedPassword);
		nguoiDung.setHoatDong(true);
		nguoiDung
				.setAnh("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMdBqlgrpFx60XH_CdP3DpEZ7oTmvQuF4i9A&s");

		nguoiDungRepository.save(nguoiDung);
	}

	public void lockUnlock(Long id) {
		NguoiDung nguoiDung = nguoiDungRepository.findById(id).orElse(null);
		if (nguoiDung != null) {
			nguoiDung.setHoatDong(!nguoiDung.getHoatDong());
			nguoiDungRepository.save(nguoiDung);
		}
	}

}
