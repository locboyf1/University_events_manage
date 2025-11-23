package com.event.university.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.university.entity.DanhGiaSuKien;
import com.event.university.entity.NguoiDung;
import com.event.university.entity.SuKien;
import com.event.university.repository.DanhGiaSuKienRepository;

@Service
public class DanhGiaSuKienService {
	@Autowired
	private DanhGiaSuKienRepository danhGiaSuKienRepository;

	public List<DanhGiaSuKien> findBySuKien(SuKien suKien) {
		return danhGiaSuKienRepository.findBySuKien(suKien);
	}

	public DanhGiaSuKien findById(Integer id) {
		return danhGiaSuKienRepository.findById(id).orElse(null);
	}

	public DanhGiaSuKien findByNguoiDungAndSuKien(NguoiDung nguoiDung, SuKien suKien) {

		return danhGiaSuKienRepository.findByNguoiDungAndSuKien(nguoiDung, suKien);
	}

	public void create(DanhGiaSuKien danhGiaSuKien, NguoiDung nguoiDung, SuKien suKien) {
		danhGiaSuKien.setNguoiDung(nguoiDung);
		danhGiaSuKien.setSuKien(suKien);
		danhGiaSuKien.setThoiGian(LocalDateTime.now());
		danhGiaSuKienRepository.save(danhGiaSuKien);
	}

	public void update(DanhGiaSuKien danhGiaSuKien) {
		DanhGiaSuKien danhGiaSuKienDB = danhGiaSuKienRepository.findById(danhGiaSuKien.getId()).orElse(null);
		danhGiaSuKienDB.setNoiDung(danhGiaSuKien.getNoiDung());
		danhGiaSuKienDB.setSoSao(danhGiaSuKien.getSoSao());
		danhGiaSuKienDB.setThoiGian(LocalDateTime.now());
		danhGiaSuKienRepository.save(danhGiaSuKienDB);
	}

	public int countBySuKien(SuKien suKien) {
		return danhGiaSuKienRepository.countBySuKien(suKien);
	}

	public double tinhSaoTrungBinh(SuKien suKien) {
		Double trungBinh = danhGiaSuKienRepository.tinhSaoTrungBinh(suKien);
		return trungBinh != null ? trungBinh : 0.0;
	}
}
