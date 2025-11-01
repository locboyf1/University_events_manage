package com.event.university.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.university.entity.Nganh;

public interface NganhRepository extends JpaRepository<Nganh, Integer> {
	List<Nganh> findByKhoaId(Integer khoaId);

	Optional<Nganh> findById(Integer id);
}