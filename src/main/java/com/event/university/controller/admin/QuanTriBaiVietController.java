package com.event.university.controller.admin;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.event.university.entity.BaiViet;
import com.event.university.entity.DanhMucBaiViet;
import com.event.university.entity.NguoiDung;
import com.event.university.repository.BaiVietRepository;
import com.event.university.service.BaiVietService;
import com.event.university.service.DanhMucBaiVietService;

import jakarta.validation.Valid;

@RequestMapping("/admin/baiviet")
@Controller
public class QuanTriBaiVietController {

	@Autowired
	DanhMucBaiVietService danhMucBaiVietService;

	@Autowired
	BaiVietRepository baiVietRepository;

	@Autowired
	BaiVietService baiVietService;

	@GetMapping({ "", "/" })
	public String index(Model model) {
		List<BaiViet> baiViets = baiVietService.findByOrderNgayTaoDesc();
		model.addAttribute("baiViets", baiViets);

		return "admin/baiviet/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		List<DanhMucBaiViet> danhMucs = danhMucBaiVietService.findByOrderByThuTu();
		model.addAttribute("danhMucs", danhMucs);

		BaiViet baiViet = new BaiViet();
		model.addAttribute("baiViet", baiViet);

		return "admin/baiviet/create";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("baiViet") BaiViet baiViet, BindingResult result, Model model,
			@AuthenticationPrincipal NguoiDung nguoiDung, MultipartFile fileAnh) throws IOException {
		if (fileAnh.isEmpty()) {
			result.rejectValue("anh", "null", "Ảnh không được để trống");
		}

		if (result.hasErrors()) {
			List<DanhMucBaiViet> danhMucs = danhMucBaiVietService.findByOrderByThuTu();
			model.addAttribute("danhMucs", danhMucs);

			return "admin/baiviet/create";
		}

		baiVietService.create(baiViet, fileAnh, nguoiDung);
		return "redirect:/admin/baiviet";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		BaiViet baiViet = baiVietService.findById(id);
		model.addAttribute("baiViet", baiViet);

		return "admin/baiviet/update";
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("baiViet") BaiViet baiViet, BindingResult result, Model model,
			MultipartFile fileAnh) throws IOException {

		if (result.hasErrors()) {
			return "admin/baiViet/update";
		}

		baiVietService.update(baiViet, fileAnh);

		return "redirect:/admin/baiviet";
	}

	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") Integer id) {
		BaiViet baiViet = baiVietService.findById(id);
		baiVietService.show(baiViet);
		return "redirect:/admin/baiviet";
	}

}
