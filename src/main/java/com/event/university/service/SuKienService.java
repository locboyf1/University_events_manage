package com.event.university.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.event.university.entity.NguoiDung;
import com.event.university.entity.SuKien;
import com.event.university.repository.SuKienRepository;
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

	public SuKien getById(Integer id) {
		return suKienRepository.findByIdAndHienThiTrueAndDuyetTrue(id);
	}

	public List<SuKien> getByNguoiDung(NguoiDung nguoiDung) {
		return suKienRepository.findByNguoiDung(nguoiDung);
	}

	public void create(SuKien suKien, MultipartFile fileAnh) throws IOException {
		byte[] anh = fileAnh.getBytes();
		String kieuAnh = fileAnh.getContentType();
		String anhBase64 = ImageProcess.convertImage2String(anh, kieuAnh);
		suKien.setAnh(anhBase64);
		suKienRepository.save(suKien);
	}
}