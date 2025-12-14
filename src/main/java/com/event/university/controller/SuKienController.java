package com.event.university.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.event.university.entity.DanhGiaSuKien;
import com.event.university.entity.DanhMucSuKien;
import com.event.university.entity.DanhSachThamGia;
import com.event.university.entity.NguoiDung;
import com.event.university.entity.SuKien;
import com.event.university.service.DanhGiaSuKienService;
import com.event.university.service.DanhMucSuKienService;
import com.event.university.service.DanhSachThamGiaService;
import com.event.university.service.NguoiDungService;
import com.event.university.service.SuKienService;

import jakarta.validation.Valid;

@Controller
public class SuKienController {

	@Autowired
	private SuKienService suKienService;

	@Autowired
	private DanhMucSuKienService danhMucSuKienService;

	@Autowired
	DanhSachThamGiaService danhSachThamGiaService;

	@Autowired
	NguoiDungService nguoiDungService;
	@Autowired
	DanhGiaSuKienService danhGiaSuKienService;

	@GetMapping({ "/sukien", "/Sukien" })
	public String index(Model model) {
		List<SuKien> suKiens = suKienService.getDisplaySorted();
		List<DanhMucSuKien> danhMucSuKiens = danhMucSuKienService.getAllDanhMucSuKien();
		model.addAttribute("suKiens", suKiens);
		model.addAttribute("danhMucSuKiens", danhMucSuKiens);
		return "event/index";
	}

	@GetMapping("/Sukien/{id}/{biDanh}.html")
	public String detail(@PathVariable Integer id, @PathVariable String biDanh, Model model, @AuthenticationPrincipal NguoiDung nguoiDungHienTai) {

		SuKien suKien = suKienService.getById(id).orElse(null);
		if (suKien == null) {
			return "event/index";
		}

		Boolean daDangKy = null;
		if (nguoiDungHienTai != null) {
			NguoiDung nguoiDung = nguoiDungService.findById(nguoiDungHienTai.getId());
			DanhSachThamGia danhSach = danhSachThamGiaService.findBySuKienAndNguoiDung(suKien, nguoiDungHienTai).orElse(null);

			if (danhSach != null) {
				daDangKy = true;
			} else {
				daDangKy = false;
			}
		}

		model.addAttribute("suKien", suKien);
		model.addAttribute("daDangKy", daDangKy);

		DanhGiaSuKien danhGiaSuKien = new DanhGiaSuKien();
		danhGiaSuKien.setSoSao(1);
		model.addAttribute("danhGiaMoi", danhGiaSuKien);

		DanhGiaSuKien danhGiaCuaBan = danhGiaSuKienService.findByNguoiDungAndSuKien(nguoiDungHienTai, suKien);
		model.addAttribute("danhGiaCuaBan", danhGiaCuaBan);

		return "event/detail";
	}

	@GetMapping("sukiencuatoi")
	public String myEvents(Model model, @AuthenticationPrincipal NguoiDung nguoiDung) {
		List<SuKien> suKiens = suKienService.getByNguoiDung(nguoiDung);
		model.addAttribute("suKiens", suKiens);
		return "event/myevents";
	}

	@GetMapping("/sukiendadangky")
	public String suKienDaDangKy(@AuthenticationPrincipal NguoiDung nguoiDung, Model model) {
		List<SuKien> suKiens = danhSachThamGiaService.getSuKienDaDangKy(nguoiDung).stream().filter(sk -> !sk.daBatDau()).toList();

		List<DanhSachThamGia> daThamGias = danhSachThamGiaService.findByNguoiDung(nguoiDung);
		List<DanhSachThamGia> daDiemDanhs = daThamGias.stream().filter(ds -> ds.getDaDiemDanh()).filter(ds -> ds.getSuKien().daBatDau()).toList();
		List<DanhSachThamGia> chuaDiemDanhs = daThamGias.stream().filter(ds -> !ds.getDaDiemDanh()).filter(ds -> ds.getSuKien().daBatDau()).toList();

		model.addAttribute("daDiemDanhs", daDiemDanhs);
		model.addAttribute("chuaDiemDanhs", chuaDiemDanhs);

		model.addAttribute("suKiens", suKiens);
		return "event/sukiendadangky";
	}

	@GetMapping("/sukiencuatoi/create")
	public String createMyEvent(@AuthenticationPrincipal NguoiDung nguoiDung, Model model) {
		SuKien suKien = new SuKien();
		suKien.setNguoiDung(nguoiDung);
		List<DanhMucSuKien> danhMucSuKiens = danhMucSuKienService.getAllOderByThuTu();
		model.addAttribute("suKien", suKien);
		model.addAttribute("danhMucs", danhMucSuKiens);
		return "event/create";
	}

	@PostMapping("/sukiencuatoi/create")
	public String createMyEvent(@AuthenticationPrincipal NguoiDung nguoiDung, @ModelAttribute SuKien suKien, MultipartFile fileAnh) throws IOException {
		suKien.setNguoiDung(nguoiDung);
		suKienService.create(suKien, fileAnh);
		return "redirect:/sukiencuatoi";
	}

	@GetMapping("/sukiencuatoi/update/{id}")
	public String updateMyEvent(@AuthenticationPrincipal NguoiDung nguoiDung, Model model, @PathVariable Integer id) {
		SuKien suKien = suKienService.getById(id).orElse(null);
		List<DanhMucSuKien> danhMucSuKiens = danhMucSuKienService.getAllOderByThuTu();
		model.addAttribute("danhMucs", danhMucSuKiens);
		model.addAttribute("suKien", suKien);
		return "/event/update";
	}

	@PostMapping("/sukiencuatoi/update")
	public String updateMyEvent(@AuthenticationPrincipal NguoiDung nguoiDung, @ModelAttribute SuKien suKien, MultipartFile fileAnh) throws IOException {

		suKienService.update(suKien, fileAnh);
		return "redirect:/sukiencuatoi";
	}

	@PostMapping("/sukiencuatoi/hidden")
	public String hidden(@RequestParam Integer id) {
		suKienService.hiddenShow(id);
		return "redirect:/sukiencuatoi";
	}

	@PostMapping("/sukien/timkiem")
	public String search(@RequestParam String keyword, Model model) {
		List<SuKien> suKiens = suKienService.search(keyword);
		List<DanhMucSuKien> danhMucSuKiens = danhMucSuKienService.getAllDanhMucSuKien();
		model.addAttribute("suKiens", suKiens);
		model.addAttribute("danhMucSuKiens", danhMucSuKiens);
		model.addAttribute("keyword", keyword);
		return "event/index";
	}

	@PostMapping("/sukien/danhgia/{suKienId}")
	public String danhGiaSuKien(@PathVariable Integer suKienId, @Valid @ModelAttribute("danhGiaMoi") DanhGiaSuKien danhGiaSuKien, BindingResult result, @AuthenticationPrincipal NguoiDung nguoiDung, Model model) {
		if (nguoiDung == null) {
			return "redirect:/dangnhap";
		}

		NguoiDung nguoiDungDB = nguoiDungService.findById(nguoiDung.getId());
		SuKien suKien = suKienService.findById(suKienId);
		if (suKien == null) {
			return "redirect:/";
		}
		DanhGiaSuKien danhGiaSuKienDB = danhGiaSuKienService.findByNguoiDungAndSuKien(nguoiDungDB, suKien);
		if (danhGiaSuKienDB != null) {
			result.rejectValue("noiDung", "", "Bạn đã đánh giá rồi");
		}
		if (result.hasErrors()) {
			Boolean daDangKy = null;
			DanhSachThamGia danhSach = danhSachThamGiaService.findBySuKienAndNguoiDung(suKien, nguoiDungDB).orElse(null);
			if (danhSach != null) {
				daDangKy = true;
			} else {
				daDangKy = false;
			}
			model.addAttribute("suKien", suKien);
			model.addAttribute("daDangKy", daDangKy);
			return "event/detail";
		}
		danhGiaSuKienService.create(danhGiaSuKien, nguoiDungDB, suKien);

		return "redirect:/Sukien/" + suKien.getId() + "/" + suKien.getBiDanh() + ".html";
	}

}
