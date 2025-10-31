package com.event.university.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.university.entity.BaiViet;

public interface BaiVietRepository extends JpaRepository<BaiViet, Integer> {

	public Optional<BaiViet> findById(Integer id);

	public List<BaiViet> findByOrderByNgayTaoDesc();

	public List<BaiViet> findByHienThiTrueOrderByNgayTaoDesc();
}
