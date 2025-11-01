package com.event.university.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.university.dto.LopDto;
import com.event.university.entity.Lop;
import com.event.university.entity.Nganh;
import com.event.university.mapper.NganhMapper;
import com.event.university.repository.LopRepository;

@Service
public class LopService {
	@Autowired
	private LopRepository lopRepository;
	@Autowired
	private NganhMapper nganhMapper;

	@Autowired
	NganhService nganhService;

	public List<Lop> findAll() {
		return lopRepository.findAll();
	}

	public Lop findById(Integer id) {
		return lopRepository.findById(id).orElse(null);
	}

	public List<LopDto> findDTOByNganhId(Integer nganhId) {
		List<Lop> lops = lopRepository.findByNganhId(nganhId);

		return lops.stream().map(nganhMapper::toLopDto).collect(Collectors.toList());
	}

	public List<Lop> findByNganhId(Integer nganhId) {
		return lopRepository.findByNganhId(nganhId);
	}

	public void create(Lop lop, Integer nganhId) {
		Nganh nganh = nganhService.findById(nganhId);
		lop.setNganh(nganh);

		lopRepository.save(lop);
	}

	public void update(Lop lop) {
		Lop lopDB = lopRepository.findById(lop.getId()).orElse(null);
		lopDB.setKhoaSo(lop.getKhoaSo());
		lopDB.setTenLop(lop.getTenLop());
		lopDB.setMoTa(lop.getMoTa());
		lopRepository.save(lopDB);
	}
}