package com.event.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.university.entity.Nganh;

public interface NganhRepository extends JpaRepository<Nganh, Long> {
	List<Nganh> findByKhoaId(Long khoaId);
}