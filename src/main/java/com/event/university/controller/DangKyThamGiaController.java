package com.event.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.university.entity.DanhSachThamGia;
import com.event.university.entity.NguoiDung;
import com.event.university.entity.SuKien;
import com.event.university.service.DanhSachThamGiaService;
import com.event.university.service.NguoiDungService;
import com.event.university.service.SuKienService;

@Controller
public class DangKyThamGiaController {

	@Autowired
	DanhSachThamGiaService danhSachThamGiaService;

	@Autowired
	NguoiDungService nguoiDungService;

	@Autowired
	SuKienService suKienService;

	@PostMapping("/dangkythamgia")
	public String register(@RequestParam("id") Integer suKienId, @AuthenticationPrincipal NguoiDung nguoiDungDangDangNhap) {

		NguoiDung nguoiDung = nguoiDungService.findById(nguoiDungDangDangNhap.getId());
		SuKien suKien = suKienService.getById(suKienId).orElse(null);

		DanhSachThamGia thamGia = danhSachThamGiaService.findBySuKienAndNguoiDung(suKien, nguoiDung).orElse(null);
		if (thamGia == null) {
			danhSachThamGiaService.addNguoiDungVaoSuKien(nguoiDung, suKienId);
		}

		return "redirect:/Sukien/" + suKien.getId() + "/" + suKien.getBiDanh() + ".html";
	}

	@PostMapping("/huydangkythamgia")
	public String unregister(@RequestParam("id") Integer suKienId, @AuthenticationPrincipal NguoiDung nguoiDungDangDangNhap) {

		NguoiDung nguoiDung = nguoiDungService.findById(nguoiDungDangDangNhap.getId());
		SuKien suKien = suKienService.getById(suKienId).orElse(null);

		DanhSachThamGia thamGia = danhSachThamGiaService.findBySuKienAndNguoiDung(suKien, nguoiDung).orElse(null);
		if (thamGia != null) {
			danhSachThamGiaService.removeNguoiDungKhoiSuKien(nguoiDung.getId(), suKienId);
		}

		return "redirect:/Sukien/" + suKien.getId() + "/" + suKien.getBiDanh() + ".html";
	}
}
