package com.event.university.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.university.entity.DanhMucSuKien;
import com.event.university.repository.DanhMucSuKienRepository;

@Service
public class DanhMucSuKienService {

	@Autowired
	private DanhMucSuKienRepository danhMucSuKienRepository;

	public List<DanhMucSuKien> getAllDanhMucSuKien() {
		return danhMucSuKienRepository.findAll();
	}

	public Optional<DanhMucSuKien> getDanhMucSuKienById(Integer id) {
		return danhMucSuKienRepository.findById(id);
	}

	public DanhMucSuKien saveDanhMucSuKien(DanhMucSuKien danhMucSuKien) {
		return danhMucSuKienRepository.save(danhMucSuKien);
	}

	public void deleteDanhMucSuKien(Integer id) {
		danhMucSuKienRepository.deleteById(id);
	}
}