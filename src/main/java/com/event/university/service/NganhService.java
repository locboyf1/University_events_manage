package com.event.university.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.university.dto.NganhDto;
import com.event.university.entity.Khoa;
import com.event.university.entity.Nganh;
import com.event.university.mapper.NganhMapper;
import com.event.university.repository.NganhRepository;

@Service
public class NganhService {
	@Autowired
	private NganhRepository nganhRepository;

	@Autowired
	private KhoaService khoaService;

	@Autowired
	private NganhMapper nganhMapper;

	public List<Nganh> findAll() {
		return nganhRepository.findAll();
	}

	public Nganh findById(Integer id) {
		return nganhRepository.findById(id).orElse(null);
	}

	public List<NganhDto> findDTOByKhoaId(Integer khoaId) {
		List<Nganh> nganhs = nganhRepository.findByKhoaId(khoaId);

		return nganhs.stream().map(nganhMapper::toNganhDto).collect(Collectors.toList());
	}

	public List<Nganh> findByKhoaId(Integer khoaId) {
		return nganhRepository.findByKhoaId(khoaId);
	}

	public void create(Nganh nganh, Integer khoaId) {
		Khoa khoa = khoaService.findById(khoaId);
		nganh.setKhoa(khoa);
		nganhRepository.save(nganh);
	}

	public void update(Nganh nganh) {
		Nganh nganhDB = nganhRepository.findById(nganh.getId()).orElse(null);
		nganhDB.setMoTa(nganh.getMoTa());
		nganhDB.setTenNganh(nganh.getTenNganh());
		nganhRepository.save(nganhDB);
	}
}