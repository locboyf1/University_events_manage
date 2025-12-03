package com.event.university.controller.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.university.entity.BaiViet;
import com.event.university.entity.DanhGiaSuKien;
import com.event.university.entity.SuKien;
import com.event.university.service.BaiVietService;
import com.event.university.service.DanhGiaSuKienService;
import com.event.university.service.DanhSachThamGiaService;
import com.event.university.service.NguoiDungService;
import com.event.university.service.SuKienService;

@Controller
@RequestMapping("/admin")
public class QuanTriController {

	@Autowired
	NguoiDungService nguoiDungService;

	@Autowired
	SuKienService suKienService;

	@Autowired
	BaiVietService baiVietService;

	@Autowired
	DanhGiaSuKienService danhGiaSuKienService;

	@Autowired
	DanhSachThamGiaService danhSachThamGiaService;

	@GetMapping({ "/bangdieukhien", "/", "" })
	public String dashboard(Model model) {
		Integer soNguoiDung = nguoiDungService.findByHoatDongTrue().size();
		Integer tongNguoiDung = nguoiDungService.getAll().size();
		Integer tyLeNguoiDungHoatDong = soNguoiDung * 100 / tongNguoiDung;

		model.addAttribute("soNguoiDung", soNguoiDung);
		model.addAttribute("tyLeNguoiDung", tyLeNguoiDungHoatDong);

		LocalDateTime bayNgayTruoc = LocalDateTime.now().minusDays(7);

		List<SuKien> chuaDienRaList = suKienService.findByDuyetTrueAndThoiGianBatDauGreaterThanNow();
		Integer chuaDienRa = chuaDienRaList.size();
		Integer chuaDienRaHien = chuaDienRaList.stream().filter(sk -> sk.isHienThi()).toList().size();
		Integer tyLeChuaDienRaHien = chuaDienRa != 0 ? chuaDienRaHien * 100 / chuaDienRa : 0;

		model.addAttribute("tyLeChuaDienRaHien", tyLeChuaDienRaHien);
		model.addAttribute("chuaDienRa", chuaDienRa);

		List<BaiViet> baiVietMoiList = baiVietService.findByNgayTaoAfter(bayNgayTruoc);
		Integer baiVietMoi = baiVietMoiList.size();
		Integer baiVietMoiHien = baiVietMoiList.stream().filter(bv -> bv.getHienThi()).toList().size();
		Integer tyLeBaiVietMoiHien = baiVietMoi != 0 ? baiVietMoiHien * 100 / baiVietMoi : 0;
		model.addAttribute("tyLeBaiVietMoiHien", tyLeBaiVietMoiHien);
		model.addAttribute("baiVietMoi", baiVietMoi);

		List<DanhGiaSuKien> danhGiaGanDayList = danhGiaSuKienService.findByThoiGianAfter(bayNgayTruoc);
		Integer danhGiaGanDay = danhGiaGanDayList.size();
		Integer danhGia5SaoGanDay = danhGiaGanDayList.stream().filter(dg -> dg.getSoSao() == 5).toList().size();
		Integer tyLeDanhGia5SaoGanDay = danhGiaGanDay != 0 ? danhGia5SaoGanDay * 100 / danhGiaGanDay : 0;
		model.addAttribute("tyLeDanhGia5SaoGanDay", tyLeDanhGia5SaoGanDay);
		model.addAttribute("danhGiaGanDay", danhGiaGanDay);

		List<String> nhans = List.of("7 ngày trước", "6 ngày trước", "5 ngày trước", "4 ngày trước", "3 ngày trước", "2 ngày trước", "Hôm qua", "Hôm nay");

		List<Integer> soLuongs = new ArrayList<>();

		for (int i = 7; i >= 0; i--) {
			LocalDate homNay = LocalDate.now();
			LocalDate ngayCanTim = homNay.minusDays(i);
			LocalDateTime dauNgayCanTim = ngayCanTim.atStartOfDay();
			LocalDateTime cuoiNgayCanTim = ngayCanTim.atTime(LocalTime.MAX);

			Integer soLuongDangKy = danhSachThamGiaService.getAll().stream().filter(dk -> dk.getThoiGianThamGia().isAfter(dauNgayCanTim) && dk.getThoiGianThamGia().isBefore(cuoiNgayCanTim)).toList().size();
			soLuongs.add(soLuongDangKy);
		}

		Map<String, Object> duLieuBieuDos = new HashMap<>();
		duLieuBieuDos.put("nhan", nhans);
		duLieuBieuDos.put("duLieu", soLuongs);

		model.addAttribute("duLieuBieuDos", duLieuBieuDos);

		return "admin/dashboard";
	}

}