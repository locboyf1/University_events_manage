package com.event.university.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.university.entity.BaiViet;
import com.event.university.entity.DanhGiaBaiViet;
import com.event.university.entity.NguoiDung;
import com.event.university.repository.DanhGiaBaiVietRepository;

@Service
public class DanhGiaBaiVietService {
	@Autowired
	private DanhGiaBaiVietRepository danhGiaBaiVietRepository;
	public List<DanhGiaBaiViet> findByBaiViet(BaiViet baiViet) {
	    return danhGiaBaiVietRepository.findByBaiViet(baiViet);
	}
	public Boolean danhGiaBaiViet(NguoiDung nguoidung, BaiViet baiviet,int sosao) {
		if (danhGiaBaiVietRepository.existsByNguoiDungAndBaiViet(nguoidung, baiviet)) {
			return false;
		}
		DanhGiaBaiViet dg = new DanhGiaBaiViet();
		dg.setNguoiDung(nguoidung);
		dg.setBaiViet(baiviet);
		dg.setSoSao((float)sosao);
		dg.setThoiGian(LocalDateTime.now());

		danhGiaBaiVietRepository.save(dg);
		return true;
	}
	public int countByBaiViet(BaiViet baiViet) {
        return danhGiaBaiVietRepository.countByBaiViet(baiViet);
    }
	public double tinhSaoTrungBinh(BaiViet baiViet) {
	    Double trungBinh = danhGiaBaiVietRepository.tinhSaoTrungBinh(baiViet);
	    return trungBinh != null ? trungBinh : 0.0;
	}
	
}
