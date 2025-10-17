package com.event.university.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.university.dto.LopDto;
import com.event.university.entity.Lop;
import com.event.university.mapper.NganhMapper;
import com.event.university.repository.LopRepository;

@Service
public class LopService {
	@Autowired
	private LopRepository lopRepository;
	@Autowired
	private NganhMapper nganhMapper;

	public List<Lop> getAll() {
		return lopRepository.findAll();
	}

	public List<LopDto> findByNganhId(Long nganhId) {
		List<Lop> lops = lopRepository.findByNganhId(nganhId);

		return lops.stream().map(nganhMapper::toLopDto).collect(Collectors.toList());
	}
}