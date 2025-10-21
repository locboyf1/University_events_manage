package com.event.university.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.event.university.entity.DanhMucSuKien;
import com.event.university.entity.NguoiDung;
import com.event.university.entity.SuKien;
import com.event.university.service.DanhMucSuKienService;
import com.event.university.service.SuKienService;
import com.event.university.utillities.Functions;

@Controller
public class EventController {

	@Autowired
	private SuKienService suKienService;

	@Autowired
	private DanhMucSuKienService danhMucSuKienService;

	@GetMapping({ "/sukien", "/Sukien" })
	public String index(Model model) {
		List<SuKien> suKiens = suKienService.getDisplaySorted();
		List<DanhMucSuKien> danhMucSuKiens = danhMucSuKienService.getAllDanhMucSuKien();
		model.addAttribute("suKiens", suKiens);
		model.addAttribute("danhMucSuKiens", danhMucSuKiens);
		return "event/index";
	}

	@GetMapping("/Sukien/{id}/{biDanh}.html")
	public String detail(@PathVariable Integer id, @PathVariable String biDanh, Model model) {
		SuKien suKien = suKienService.getById(id);
		if (suKien == null) {
			return "event/index";
		}

		model.addAttribute("suKien", suKien);

		return "event/detail";
	}

	@GetMapping("sukiencuatoi")
	public String myEvents(Model model, @AuthenticationPrincipal NguoiDung nguoiDung) {
		List<SuKien> suKiens = suKienService.getByNguoiDung(nguoiDung);
		model.addAttribute("suKiens", suKiens);
		return "event/myevents";
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
	public String createMyEvent(@AuthenticationPrincipal NguoiDung nguoiDung, @ModelAttribute SuKien suKien,
			MultipartFile fileAnh) throws IOException {
		suKien.setThoiGianTao(LocalDateTime.now());
		suKien.setBiDanh(Functions.convertStringToAlias(suKien.getTenSuKien()));
		suKien.setDuyet(false);
		suKien.setNguoiDung(nguoiDung);
		suKienService.create(suKien, fileAnh);
		return "redirect:/sukiencuatoi";
	}
}
