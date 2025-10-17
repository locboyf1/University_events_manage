package com.event.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.university.entity.VaiTro;

public interface VaiTroRepository extends JpaRepository<VaiTro, Long> {
	VaiTro findByBiDanh(String biDanh);
}