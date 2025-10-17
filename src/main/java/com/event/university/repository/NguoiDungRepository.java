package com.event.university.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.university.entity.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {

	Optional<NguoiDung> findByEmail(String email);

	List<NguoiDung> findByHoatDongTrue();

	Optional<NguoiDung> findByTaiKhoan(String taiKhoan);

	Optional<NguoiDung> findById(Long id);
}
