package com.event.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.university.entity.Khoa;
import com.event.university.repository.KhoaRepository;

@Service
public class KhoaService {
	@Autowired
	private KhoaRepository khoaRepository;

	public List<Khoa> getAll() {
		return khoaRepository.findAll();
	}

	public Khoa findById(Integer id) {
		return khoaRepository.findById(id).orElse(null);
	}

	public void update(Khoa khoa) {
		Khoa khoaDB = khoaRepository.findById(khoa.getId()).orElse(null);
		khoaDB.setMoTa(khoa.getMoTa());
		khoaDB.setTenKhoa(khoa.getTenKhoa());
		khoaRepository.save(khoaDB);
	}
}