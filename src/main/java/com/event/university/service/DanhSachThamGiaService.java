package com.event.university.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.event.university.entity.DanhSachThamGia;
import com.event.university.entity.NguoiDung;
import com.event.university.entity.SuKien;
import com.event.university.repository.DanhSachThamGiaRepository;
import com.event.university.repository.SuKienRepository;
import com.event.university.utillities.ImageProcess;

@Service
public class DanhSachThamGiaService {

	@Autowired
	private DanhSachThamGiaRepository danhSachThamGiaRepository;

	@Autowired
	private SuKienRepository suKienRepository;

	public List<DanhSachThamGia> getBySuKienId(Integer suKienId) {
		return danhSachThamGiaRepository.findBySuKien_Id(suKienId);
	}

	public Optional<DanhSachThamGia> findBySuKienAndNguoiDung(SuKien suKien, NguoiDung nguoiDung) {
		return danhSachThamGiaRepository.findBySuKienAndNguoiDung(suKien, nguoiDung);
	}

	public DanhSachThamGia findById(Integer id) {
		return danhSachThamGiaRepository.findById(id).orElse(null);
	}

	public void diemdanh(DanhSachThamGia thamGia, MultipartFile fileAnh) throws Exception {
		byte[] anhBase64 = fileAnh.getBytes();
		String kieuAnh = fileAnh.getContentType();
		thamGia.setAnhDiemDanh(ImageProcess.convertImage2String(anhBase64, kieuAnh));
		thamGia.setThoiGianDiemDanh(LocalDateTime.now());
		danhSachThamGiaRepository.save(thamGia);
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
		thamGia.setThoiGianThamGia(LocalDateTime.now());

		danhSachThamGiaRepository.save(thamGia);
	}

	@Transactional
	public void removeNguoiDungKhoiSuKien(Integer suKienId, Integer nguoiDungId) {
		danhSachThamGiaRepository.deleteBySuKien_IdAndNguoiDung_Id(nguoiDungId, suKienId);
	}

	public List<SuKien> getSuKienDaDangKy(NguoiDung nguoiDung) {
		List<DanhSachThamGia> thamGiaList = danhSachThamGiaRepository.findByNguoiDung(nguoiDung);
		return thamGiaList.stream().map(DanhSachThamGia::getSuKien).toList();
	}

	public void diemDanh(DanhSachThamGia thamGia, String anhBase64) {
		thamGia.setAnhDiemDanh(anhBase64);
		thamGia.setThoiGianDiemDanh(LocalDateTime.now());
		danhSachThamGiaRepository.save(thamGia);
	}
}
