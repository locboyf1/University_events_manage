package com.event.university.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.event.university.entity.DanhSachThamGia;
import com.event.university.entity.NguoiDung;
import com.event.university.entity.SuKien;
import com.event.university.repository.DanhSachThamGiaRepository;
import com.event.university.repository.SuKienRepository;

@Service
public class DanhSachThamGiaService {

	@Autowired
	private DanhSachThamGiaRepository danhSachThamGiaRepository;

	@Autowired
	private SuKienRepository suKienRepository;

	/**
	 * 🔹 Lấy danh sách người tham gia theo mã sự kiện
	 */
	public List<DanhSachThamGia> getBySuKienId(Integer suKienId) {
		return danhSachThamGiaRepository.findBySuKien_Id(suKienId);
	}

	public void addNguoiDungVaoSuKien(NguoiDung nguoiDung, Integer suKienId) {
		Optional<SuKien> suKienOpt = suKienRepository.findById(suKienId);
		if (suKienOpt.isEmpty()) {
			throw new IllegalArgumentException("Không tìm thấy sự kiện có ID = " + suKienId);
		}

		SuKien suKien = suKienOpt.get();

		Optional<DanhSachThamGia> existing = danhSachThamGiaRepository.findBySuKienAndNguoiDung(suKien, nguoiDung);
		if (existing.isPresent()) {
			throw new IllegalArgumentException("Người dùng này đã tham gia sự kiện!");
		}

		DanhSachThamGia thamGia = new DanhSachThamGia();
		thamGia.setSuKien(suKien);
		thamGia.setNguoiDung(nguoiDung);

		danhSachThamGiaRepository.save(thamGia);
	}

	@Transactional
	public void removeNguoiDungKhoiSuKien(Integer suKienId, Integer nguoiDungId) {
		danhSachThamGiaRepository.deleteBySuKien_IdAndNguoiDung_Id(nguoiDungId, suKienId);
	}

}
