package com.event.university.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.event.university.dto.NguoiDungDto;
import com.event.university.entity.NguoiDung;
import com.event.university.repository.NguoiDungRepository;
import com.event.university.utillities.ImageProcess;

@Service
public class NguoiDungService implements UserDetailsService {

	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	@Autowired
	private LopService lopService;

	@Autowired
	private VaiTroService vaiTroService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Optional<NguoiDung> findByTaiKhoan(String taiKhoan) {
		return nguoiDungRepository.findByTaiKhoan(taiKhoan);
	}

	public List<NguoiDung> findAllByTaiKhoan(String taiKhoan) {
		return nguoiDungRepository.findAllByTaiKhoan(taiKhoan);
	}

	public List<NguoiDung> findSinhVienHoatDong() {
		return nguoiDungRepository.findHoatDongSinhVien();
	}

	public List<NguoiDung> getAll() {
		return nguoiDungRepository.findAll();

	}

	public NguoiDung findById(Integer id) {
		return nguoiDungRepository.findById(id).orElse(null);
	}

	public NguoiDung getFromDto(NguoiDungDto nguoiDungDto) {
		NguoiDung nguoiDung = new NguoiDung();
		nguoiDung.setHoTen(nguoiDungDto.getHoTen());
		nguoiDung.setLop(lopService.findById(nguoiDungDto.getMaLop()));
		nguoiDung.setVaiTro(vaiTroService.findById(nguoiDungDto.getMaVaiTro()));
		nguoiDung.setMatKhau(nguoiDungDto.getMatKhau());
		nguoiDung.setTaiKhoan(nguoiDungDto.getTaiKhoan());
		return nguoiDung;
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
