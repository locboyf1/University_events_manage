package com.event.university.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.university.entity.DanhMucBaiViet;
import com.event.university.repository.DanhMucBaiVietRepository;
import com.event.university.service.DanhMucBaiVietService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/danhmucbaiviet")
public class DanhMucBaiVietController {

	@Autowired
	DanhMucBaiVietRepository danhMucBaiVietRepository;

	@Autowired
	DanhMucBaiVietService danhMucBaiVietService;

	@GetMapping({ "/", "" })
	public String index(Model model) {
		List<DanhMucBaiViet> danhMucs = danhMucBaiVietService.findByOrderByThuTu();
		model.addAttribute("danhMucs", danhMucs);

		DanhMucBaiViet danhMuc = new DanhMucBaiViet();
		model.addAttribute("danhMuc", danhMuc);
		return "admin/danhmucbaiviet/index";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("danhMuc") DanhMucBaiViet danhMuc, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<DanhMucBaiViet> danhMucs = danhMucBaiVietService.findByOrderByThuTu();
			model.addAttribute("danhMucs", danhMucs);

			return "admin/danhmucbaiviet/index";
		}

		danhMucBaiVietService.create(danhMuc);

		return "redirect:/admin/danhmucbaiviet";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		DanhMucBaiViet danhMuc = danhMucBaiVietService.findById(id);
		model.addAttribute("danhMuc", danhMuc);
		return "admin/danhmucbaiviet/update";
	}

	@PostMapping("update")
	public String update(@Valid @ModelAttribute("danhMuc") DanhMucBaiViet danhMuc, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin/danhmucbaiviet/update";
		}
		danhMucBaiVietService.update(danhMuc);

		return "redirect:/admin/danhmucbaiviet";
	}

	@GetMapping("/up/{id}")
	public String up(@PathVariable("id") Integer id) {
		DanhMucBaiViet danhMuc = danhMucBaiVietService.findById(id);
		danhMucBaiVietService.up(danhMuc);
		return "redirect:/admin/danhmucbaiviet";
	}

	@GetMapping("/down/{id}")
	public String down(@PathVariable("id") Integer id) {
		DanhMucBaiViet danhMuc = danhMucBaiVietService.findById(id);
		danhMucBaiVietService.down(danhMuc);
		return "redirect:/admin/danhmucbaiviet";
	}

}
