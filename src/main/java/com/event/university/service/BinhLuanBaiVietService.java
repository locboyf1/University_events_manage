package com.event.university.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.university.entity.BaiViet;
import com.event.university.entity.BinhLuanBaiViet;
import com.event.university.entity.NguoiDung;
import com.event.university.repository.BinhLuanBaiVietRepository;

@Service
public class BinhLuanBaiVietService {

	@Autowired
	BinhLuanBaiVietRepository binhLuanBaiVietRepository;

	public List<BinhLuanBaiViet> findByBaiViet(BaiViet baiViet) {
		return binhLuanBaiVietRepository.findByBaiViet(baiViet);
	}

	public void create(BinhLuanBaiViet binhLuan, BaiViet baiViet, NguoiDung nguoiDung) {
		binhLuan.setBaiViet(baiViet);
		binhLuan.setNguoiDung(nguoiDung);
		binhLuan.setThoiGian(LocalDateTime.now());
		binhLuanBaiVietRepository.save(binhLuan);
	}
}
