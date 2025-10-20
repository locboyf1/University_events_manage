package com.event.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.university.entity.Lop;

public interface LopRepository extends JpaRepository<Lop, Integer> {
	List<Lop> findByNganhId(Integer nganhId);
}