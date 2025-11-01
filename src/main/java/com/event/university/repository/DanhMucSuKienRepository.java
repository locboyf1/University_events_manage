package com.event.university.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.university.entity.DanhMucSuKien;

@Repository
public interface DanhMucSuKienRepository extends JpaRepository<DanhMucSuKien, Integer> {
	public List<DanhMucSuKien> findAllByOrderByThuTu();

	public DanhMucSuKien findTopByOrderByThuTuDesc();

	public Optional<DanhMucSuKien> findByThuTu(Integer thuTu);

	public Optional<DanhMucSuKien> findById(Integer Id);
}