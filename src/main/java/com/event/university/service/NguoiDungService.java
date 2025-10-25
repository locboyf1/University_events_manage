package com.event.university.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.event.university.entity.NguoiDung;
import com.event.university.repository.NguoiDungRepository;
import com.event.university.utillities.ImageProcess;

@Service
public class NguoiDungService implements UserDetailsService {

	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<NguoiDung> getAll() {
		return nguoiDungRepository.findAll();

	}

	public NguoiDung getById(Integer id) {
		return nguoiDungRepository.findById(id).orElse(null);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		NguoiDung nguoiDung = nguoiDungRepository.findByTaiKhoan(username)
				.orElseThrow(() -> new UsernameNotFoundException("Sai thông tin đăng nhập"));

		return nguoiDung;
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

	public void create(NguoiDung nguoiDung) {

		String rawPassword = nguoiDung.getMatKhau();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		nguoiDung.setMatKhau(encodedPassword);
		nguoiDung.setHoatDong(true);
		nguoiDung
				.setAnh("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMdBqlgrpFx60XH_CdP3DpEZ7oTmvQuF4i9A&s");

		nguoiDungRepository.save(nguoiDung);
	}

	public void lockUnlock(Integer id) {
		NguoiDung nguoiDung = nguoiDungRepository.findById(id).orElse(null);
		if (nguoiDung != null) {
			nguoiDung.setHoatDong(!nguoiDung.getHoatDong());
			nguoiDungRepository.save(nguoiDung);
		}
	}

	public void changeAvatar(NguoiDung nguoiDung, MultipartFile fileAnh) throws IOException {
		NguoiDung nguoiDungDB = nguoiDungRepository.findById(nguoiDung.getId()).orElse(null);

		byte[] anh = fileAnh.getBytes();
		String kieuAnh = fileAnh.getContentType();
		String anhBase64 = ImageProcess.convertImage2String(anh, kieuAnh);
		nguoiDungDB.setAnh(anhBase64);
		nguoiDungRepository.save(nguoiDungDB);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.getPrincipal() instanceof NguoiDung) {

			NguoiDung sessionNguoiDung = (NguoiDung) authentication.getPrincipal();
			if (sessionNguoiDung.getId().equals(nguoiDung.getId())) {

				sessionNguoiDung.setAnh(anhBase64);
			}

		}
	}

	public void changeEmailAndPhone(NguoiDung nguoiDung) {
		NguoiDung nguoiDungPreviou = nguoiDungRepository.findById(nguoiDung.getId()).orElse(null);
		nguoiDungPreviou.setEmail(nguoiDung.getEmail());
		nguoiDungPreviou.setSDT(nguoiDung.getSDT());

		nguoiDungRepository.save(nguoiDungPreviou);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.getPrincipal() instanceof NguoiDung) {

			NguoiDung sessionNguoiDung = (NguoiDung) authentication.getPrincipal();
			if (sessionNguoiDung.getId().equals(nguoiDungPreviou.getId())) {

				sessionNguoiDung.setEmail(nguoiDungPreviou.getEmail());
				sessionNguoiDung.setSDT(nguoiDungPreviou.getSDT());
			}

		}
	}
}
