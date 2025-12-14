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

	public DanhMucBaiViet findById(Integer id) {
		return danhMucBaiVietRepository.findById(id).orElse(null);
	}

	public List<DanhMucBaiViet> findByOrderByThuTu() {
		return danhMucBaiVietRepository.findByOrderByThuTu();
	}

	public void create(DanhMucBaiViet danhMuc) {
		DanhMucBaiViet danhMucTruocDo = danhMucBaiVietRepository.findTopByOrderByThuTuDesc();
		danhMuc.setThuTu(danhMucTruocDo.getThuTu() + 1);

		danhMucBaiVietRepository.save(danhMuc);
	}

	public void update(DanhMucBaiViet danhMuc) {
		danhMucBaiVietRepository.save(danhMuc);
	}
	public void up(DanhMucBaiViet danhMuc) {
		if (danhMuc.getThuTu() != 1) {
			DanhMucBaiViet danhMucDungTruoc = danhMucBaiVietRepository.findByThuTu(danhMuc.getThuTu() - 1);
			danhMucDungTruoc.setThuTu(danhMuc.getThuTu());
			danhMuc.setThuTu(danhMuc.getThuTu() - 1);

			danhMucBaiVietRepository.save(danhMuc);
			danhMucBaiVietRepository.save(danhMucDungTruoc);
		}

	}
	public void down(DanhMucBaiViet danhMuc) {
		if (danhMuc.getThuTu() != danhMucBaiVietRepository.findTopByOrderByThuTuDesc().getThuTu()) {
			DanhMucBaiViet danhMucDungSau = danhMucBaiVietRepository.findByThuTu(danhMuc.getThuTu() + 1);
			danhMucDungSau.setThuTu(danhMuc.getThuTu());
			danhMuc.setThuTu(danhMuc.getThuTu() + 1);

			danhMucBaiVietRepository.save(danhMuc);
			danhMucBaiVietRepository.save(danhMucDungSau);
		}

	}
}
