package com.event.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.university.entity.DanhMucBaiViet;

public interface DanhMucBaiVietRepository extends JpaRepository<DanhMucBaiViet, Integer> {

	public List<DanhMucBaiViet> findByOrderByThuTu();

}
