package com.event.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.university.entity.VaiTro;
import com.event.university.repository.VaiTroRepository;

@Service
public class VaiTroService {
	@Autowired
	private VaiTroRepository vaiTroRepository;

	public List<VaiTro> getAll() {
		return vaiTroRepository.findAll();
	}

	public VaiTro findByAlias(String biDanh) {
		return vaiTroRepository.findByBiDanh(biDanh);
	}
}