package com.event.university.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.university.entity.DanhMucSuKien;
import com.event.university.repository.DanhMucSuKienRepository;
import com.event.university.utillities.Functions;

@Service
public class DanhMucSuKienService {

	@Autowired
	private DanhMucSuKienRepository danhMucSuKienRepository;

	public List<DanhMucSuKien> getAllDanhMucSuKien() {
		return danhMucSuKienRepository.findAll();
	}

	public Optional<DanhMucSuKien> getDanhMucSuKienById(Integer id) {
		return danhMucSuKienRepository.findById(id);
	}

	public DanhMucSuKien saveDanhMucSuKien(DanhMucSuKien danhMucSuKien) {
		return danhMucSuKienRepository.save(danhMucSuKien);
	}

	public List<DanhMucSuKien> getAllOderByThuTu() {
		return danhMucSuKienRepository.findAllByOrderByThuTu();
	}

	public DanhMucSuKien findByThuTu(Integer thuTu) {
		return danhMucSuKienRepository.findByThuTu(thuTu).orElse(null);
	}

	public void create(DanhMucSuKien danhMuc) {
		DanhMucSuKien danhMucTruocDo = danhMucSuKienRepository.findTopByOrderByThuTuDesc();
		danhMuc.setThuTu(danhMucTruocDo.getThuTu() + 1);
		danhMuc.setBiDanh(Functions.convertStringToAlias(danhMuc.getTenDanhMuc()));
		danhMucSuKienRepository.save(danhMuc);
	}

	public void update(DanhMucSuKien danhMuc) {
		danhMuc.setBiDanh(Functions.convertStringToAlias(danhMuc.getTenDanhMuc()));
		danhMucSuKienRepository.save(danhMuc);
	}

	public void up(DanhMucSuKien danhMuc) {
		if (danhMuc.getThuTu() != 1) {
			DanhMucSuKien danhMucDungTruoc = danhMucSuKienRepository.findByThuTu(danhMuc.getThuTu() - 1).orElse(null);
			danhMucDungTruoc.setThuTu(danhMuc.getThuTu());
			danhMuc.setThuTu(danhMuc.getThuTu() - 1);

			danhMucSuKienRepository.save(danhMuc);
			danhMucSuKienRepository.save(danhMucDungTruoc);
		}

	}

	public void down(DanhMucSuKien danhMuc) {
		if (danhMuc.getThuTu() != danhMucSuKienRepository.findTopByOrderByThuTuDesc().getThuTu()) {
			DanhMucSuKien danhMucDungSau = danhMucSuKienRepository.findByThuTu(danhMuc.getThuTu() + 1).orElse(null);
			danhMucDungSau.setThuTu(danhMuc.getThuTu());
			danhMuc.setThuTu(danhMuc.getThuTu() + 1);

			danhMucSuKienRepository.save(danhMuc);
			danhMucSuKienRepository.save(danhMucDungSau);
		}

	}

}