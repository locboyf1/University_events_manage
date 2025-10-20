package com.event.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.university.entity.VaiTro;

public interface VaiTroRepository extends JpaRepository<VaiTro, Integer> {
	VaiTro findByBiDanh(String biDanh);
}