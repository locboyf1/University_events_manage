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
	
	public Boolean danhGiaSuKien(NguoiDung nguoiDung, SuKien suKien, float soSao) {
	    if (danhGiaSuKienRepository.existsByNguoiDungAndSuKien(nguoiDung, suKien)) {
	        return false;  
	    }

	    DanhGiaSuKien dg = new DanhGiaSuKien();
	    dg.setNguoiDung(nguoiDung);
	    dg.setSuKien(suKien);
	    dg.setSoSao((float) soSao);	
	    dg.setThoiGian(LocalDateTime.now());

	    danhGiaSuKienRepository.save(dg);
	    return true;
	}
	public int countBySuKien(SuKien suKien) {
		return danhGiaSuKienRepository.countBySuKien(suKien);
	}
	public double tinhSaoTrungBinh(SuKien suKien) {
	    Double trungBinh = danhGiaSuKienRepository.tinhSaoTrungBinh(suKien);
	    return trungBinh != null ? trungBinh : 0.0;
	}
}
