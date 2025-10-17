package com.event.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.university.entity.SuKien;

@Repository
public interface SuKienRepository extends JpaRepository<SuKien, Long> {

	List<SuKien> findByHienThiTrueOrderByThoiGianBatDauDesc();

	SuKien findByIdAndHienThiTrue(Long id);

}