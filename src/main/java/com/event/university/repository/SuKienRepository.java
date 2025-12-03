package com.event.university.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.university.entity.NguoiDung;
import com.event.university.entity.SuKien;

@Repository
public interface SuKienRepository extends JpaRepository<SuKien, Integer> {

	List<SuKien> findByHienThiTrueAndDuyetTrueOrderByThoiGianBatDauDesc();

	Optional<SuKien> findById(Integer id);

	List<SuKien> findByNguoiDung(NguoiDung nguoiDung);

	List<SuKien> findByDuyetFalseAndThoiGianBatDauGreaterThan(LocalDateTime now);

	List<SuKien> findByTenSuKienContainingIgnoreCaseAndHienThiTrueAndDuyetTrueAndThoiGianBatDauGreaterThanEqual(String keyword, LocalDateTime now);

	List<SuKien> findByDuyetTrueAndThoiGianBatDauGreaterThan(LocalDateTime now);
}