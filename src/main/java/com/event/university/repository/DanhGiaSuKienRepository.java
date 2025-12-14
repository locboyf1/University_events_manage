package com.event.university.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.event.university.entity.DanhGiaSuKien;
import com.event.university.entity.NguoiDung;
import com.event.university.entity.SuKien;

public interface DanhGiaSuKienRepository extends JpaRepository<DanhGiaSuKien, Integer> {

	Boolean existsByNguoiDungAndSuKien(NguoiDung nguoiDung, SuKien suKien);

	List<DanhGiaSuKien> findBySuKien(SuKien suKien);

	int countBySuKien(SuKien suKien);

	@Query("SELECT AVG(d.soSao) FROM DanhGiaSuKien d WHERE d.suKien = :suKien")
	Double tinhSaoTrungBinh(@Param("suKien") SuKien suKien);

	DanhGiaSuKien findByNguoiDungAndSuKien(NguoiDung nguoiDung, SuKien suKien);

	List<DanhGiaSuKien> findByThoiGianAfter(LocalDateTime time);
}
