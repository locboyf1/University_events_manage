package com.event.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.university.entity.BaiViet;
import com.event.university.entity.BinhLuanBaiViet;
import com.event.university.entity.NguoiDung;
import com.event.university.service.BaiVietService;
import com.event.university.service.BinhLuanBaiVietService;
import com.event.university.service.NguoiDungService;

import jakarta.validation.Valid;

@Controller
public class BaiVietController {

	final static Integer KICHTHUOCTRANG = 6;

	@Autowired
	BaiVietService baiVietService;

	@Autowired
	NguoiDungService nguoiDungService;

	@Autowired
	BinhLuanBaiVietService binhLuanBaiVietService;

	@GetMapping("/baiviet")
	public String index(@RequestParam(name = "keyword", defaultValue = "") String tuKhoa,
			@RequestParam(name = "page", defaultValue = "1") Integer trang, Model model) {
		if (trang <= 0) {
			trang = 1;
			return "redirect:/baiviet?keyword=" + tuKhoa + "&page=" + trang;
		}

		Page<BaiViet> truyVan = baiVietService.findByKeyword(tuKhoa, trang, KICHTHUOCTRANG);
		Integer tongTrang = truyVan.getTotalPages();

		if (trang > tongTrang && tongTrang > 0) {
			trang = tongTrang;
			return "redirect:/baiviet?keyword=" + tuKhoa + "&page=" + trang;
		}

		model.addAttribute("truyVan", truyVan);

		model.addAttribute("keyword", tuKhoa);
		List<BaiViet> topBinhLuan = baiVietService.layTopBaiVietNhieuBinhLuan(5);

		model.addAttribute("topBinhLuan", topBinhLuan);
		return "baiviet/index";
	}

	@GetMapping("/baiviet/{id}/{alias}")
	public String detail(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal NguoiDung nguoiDung) {
		BaiViet baiViet = baiVietService.findById(id);
		List<BaiViet> baiVietLienQuan =
		            baiVietService.layCacBaiVietLienQuan(
		                    baiViet.getDanhMucBaiViet(),
		                    baiViet.getId()
		            );
		   
		model.addAttribute("baiVietLienQuan", baiVietLienQuan);
		model.addAttribute("baiViet", baiViet);

		NguoiDung nguoiDungDB = null;

		if (nguoiDung != null) {
			nguoiDungDB = nguoiDungService.findById(nguoiDung.getId());
		}
		model.addAttribute("nguoiDung", nguoiDungDB);

		List<BinhLuanBaiViet> binhLuans = binhLuanBaiVietService.findByBaiViet(baiViet);
		model.addAttribute("binhLuans", binhLuans);

		BinhLuanBaiViet binhLuanMoi = new BinhLuanBaiViet();
		model.addAttribute("binhLuanMoi", binhLuanMoi);

		return "baiviet/detail";
	}

	@PostMapping("/baiviet/binhluan/{eventId}")
	public String comment(@PathVariable("eventId") Integer eventId,
			@Valid @ModelAttribute("binhLuanMoi") BinhLuanBaiViet binhLuan, BindingResult result,
			@AuthenticationPrincipal NguoiDung nguoiDung, Model model) {

		NguoiDung nguoiDungDB = nguoiDungService.findById(nguoiDung.getId());

		BaiViet baiViet = baiVietService.findById(eventId);
		if (baiViet == null) {
			return "redirect:/";
		}

		if (result.hasErrors()) {
			model.addAttribute("baiViet", baiViet);

			List<BinhLuanBaiViet> binhLuans = binhLuanBaiVietService.findByBaiViet(baiViet);
			model.addAttribute("binhLuans", binhLuans);

			model.addAttribute("nguoiDung", nguoiDungDB);

			return "baiviet/detail";
		}

		binhLuanBaiVietService.create(binhLuan, baiViet, nguoiDungDB);

		return "redirect:/baiviet/" + baiViet.getId() + "/" + baiViet.getBiDanh();
	}
}
