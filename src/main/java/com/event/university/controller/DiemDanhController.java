package com.event.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.university.entity.DanhSachThamGia;
import com.event.university.entity.SuKien;
import com.event.university.service.DanhSachThamGiaService;
import com.event.university.service.NguoiDungService;
import com.event.university.service.SuKienService;

@Controller
public class DiemDanhController {

	@Autowired
	SuKienService suKienService;
	@Autowired
	NguoiDungService nguoiDungService;
	@Autowired
	DanhSachThamGiaService danhSachThamGiaService;

	@Transactional
	@PostMapping("/diemdanh/{id}")
	public String index(Model model, @PathVariable("id") Integer id) {
		DanhSachThamGia thamGia = danhSachThamGiaService.findById(id);
		if (thamGia == null) {
			return "redirect:/sukiendadangky";
		}
		model.addAttribute("thamGia", thamGia);
		model.addAttribute("suKien", thamGia.getSuKien());
		return "event/diemdanh";
	}

	@PostMapping("/luudiemdanh/{id}")
	public String diemdanh(Model model, @PathVariable("id") Integer id, @RequestParam("anhBase64") String anhBase64) {

		DanhSachThamGia thamGia = danhSachThamGiaService.findById(id);
		danhSachThamGiaService.diemDanh(thamGia, anhBase64);

		return "redirect:/sukiendadangky";

	}

	@GetMapping("/danhsachthamgia/{id}")
	public String danhsachthamgia(Model model, @PathVariable("id") Integer id) {

		SuKien suKien = suKienService.getById(id).orElse(null);
		if (suKien == null) {
			return "redirect:/sukiencuatoi";
		}

		List<DanhSachThamGia> danhSach = danhSachThamGiaService.getBySuKienId(suKien.getId());
		List<DanhSachThamGia> daDiemDanhs = danhSach.stream().filter(ds -> ds.getDaDiemDanh()).toList();
		List<DanhSachThamGia> chuaDiemDanhs = danhSach.stream().filter(ds -> !ds.getDaDiemDanh()).toList();

		model.addAttribute("chuaDiemDanhs", chuaDiemDanhs);
		model.addAttribute("daDiemDanhs", daDiemDanhs);
		model.addAttribute("suKien", suKien);

		return "event/danhsachthamgia";

	}

}
