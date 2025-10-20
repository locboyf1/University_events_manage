package com.event.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.university.entity.NguoiDung;
import com.event.university.entity.SuKien;
import com.event.university.repository.SuKienRepository;

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
}