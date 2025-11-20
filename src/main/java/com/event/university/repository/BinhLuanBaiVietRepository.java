package com.event.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.university.entity.BaiViet;
import com.event.university.entity.BinhLuanBaiViet;

public interface BinhLuanBaiVietRepository extends JpaRepository<BinhLuanBaiViet, Integer> {

	public List<BinhLuanBaiViet> findByBaiViet(BaiViet baiViet);
}
