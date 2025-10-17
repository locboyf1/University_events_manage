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
}