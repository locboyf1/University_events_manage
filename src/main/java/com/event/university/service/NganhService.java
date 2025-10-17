package com.event.university.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.university.dto.NganhDto;
import com.event.university.entity.Nganh;
import com.event.university.mapper.NganhMapper;
import com.event.university.repository.NganhRepository;

@Service
public class NganhService {
	@Autowired
	private NganhRepository nganhRepository;

	@Autowired
	private NganhMapper nganhMapper;

	public List<Nganh> getAll() {
		return nganhRepository.findAll();
	}

	public List<NganhDto> findByKhoaId(Long khoaId) {
		List<Nganh> nganhs = nganhRepository.findByKhoaId(khoaId);

		return nganhs.stream().map(nganhMapper::toNganhDto).collect(Collectors.toList());
	}
}