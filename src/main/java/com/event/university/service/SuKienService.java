package com.event.university.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.event.university.entity.NguoiDung;
import com.event.university.entity.SuKien;
import com.event.university.repository.SuKienRepository;
import com.event.university.utillities.Functions;
import com.event.university.utillities.ImageProcess;

@Service
public class SuKienService {

	@Autowired
	private SuKienRepository suKienRepository;

	public List<SuKien> getAllSuKien() {
		return suKienRepository.findAll();
	}

	public List<SuKien> getDisplaySorted() {
		return suKienRepository.findByHienThiTrueAndDuyetTrueOrderByThoiGianBatDauDesc();
	}

	public Optional<SuKien> getById(Integer id) {
		return suKienRepository.findById(id);
	}

	public List<SuKien> getByNguoiDung(NguoiDung nguoiDung) {
		return suKienRepository.findByNguoiDung(nguoiDung);
	}

	public List<SuKien> findByDuyetFalseAndThoiGianBatDauGreaterThanNow() {
		LocalDateTime now = LocalDateTime.now();
		return suKienRepository.findByDuyetFalseAndThoiGianBatDauGreaterThan(now);
	}

	public void accept(SuKien suKien) {
		suKien.setDuyet(true);
		suKienRepository.save(suKien);
	}

	public void create(SuKien suKien, MultipartFile fileAnh) throws IOException {
		byte[] anh = fileAnh.getBytes();
		String kieuAnh = fileAnh.getContentType();
		String anhBase64 = ImageProcess.convertImage2String(anh, kieuAnh);
		suKien.setAnh(anhBase64);
		suKien.setThoiGianTao(LocalDateTime.now());
		suKien.setBiDanh(Functions.convertStringToAlias(suKien.getTenSuKien()));
		suKien.setDuyet(false);
		suKienRepository.save(suKien);
	}

	public void update(SuKien suKienUpdated, MultipartFile fileAnh) throws IOException {
		SuKien suKienPreviou = suKienRepository.findById(suKienUpdated.getId()).orElse(null);
		if (fileAnh.isEmpty()) {
			suKienUpdated.setAnh(suKienPreviou.getAnh());
		} else {
			byte[] anh = fileAnh.getBytes();
			String kieuAnh = fileAnh.getContentType();
			String anhBase64 = ImageProcess.convertImage2String(anh, kieuAnh);
			suKienUpdated.setAnh(anhBase64);
		}
		suKienUpdated.setBiDanh(Functions.convertStringToAlias(suKienUpdated.getTenSuKien()));
		suKienUpdated.setThoiGianTao(suKienPreviou.getThoiGianTao());
		suKienUpdated.setThoiGianSuaGanNhat(LocalDateTime.now());
		suKienUpdated.setDuyet(false);
		suKienUpdated.setDanhSachThamGia(suKienPreviou.getDanhSachThamGia());
		suKienUpdated.setNguoiDung(suKienPreviou.getNguoiDung());
		suKienRepository.save(suKienUpdated);
	}

	public void hiddenShow(Integer id) {
		SuKien suKien = suKienRepository.findById(id).orElse(null);
		suKien.setHienThi(!suKien.isHienThi());
		suKienRepository.save(suKien);
	}

	public List<SuKien> search(String keyword) {
		LocalDateTime now = LocalDateTime.now();
		if (keyword == null || keyword.trim().isEmpty()) {
			keyword = "";
		}
		return suKienRepository.findByTenSuKienContainingIgnoreCaseAndHienThiTrueAndDuyetTrueAndThoiGianBatDauGreaterThanEqual(keyword, now);
	}
}