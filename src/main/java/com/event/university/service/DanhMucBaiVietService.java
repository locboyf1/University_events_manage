package com.event.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.university.entity.DanhMucBaiViet;
import com.event.university.repository.DanhMucBaiVietRepository;

@Service
public class DanhMucBaiVietService {

	@Autowired
	DanhMucBaiVietRepository danhMucBaiVietRepository;

	public List<DanhMucBaiViet> findByOrderByThuTu() {
		return danhMucBaiVietRepository.findByOrderByThuTu();
	}
}
