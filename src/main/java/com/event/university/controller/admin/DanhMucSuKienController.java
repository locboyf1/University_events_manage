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

import com.event.university.entity.DanhMucSuKien;
import com.event.university.repository.DanhMucSuKienRepository;
import com.event.university.service.DanhMucSuKienService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/danhmucsukien")
public class DanhMucSuKienController {

	@Autowired
	DanhMucSuKienRepository danhMucSuKienRepository;

	@Autowired
	DanhMucSuKienService danhMucSuKienService;

	@GetMapping({ "/", "" })
	public String index(Model model) {
		DanhMucSuKien danhMuc = new DanhMucSuKien();
		model.addAttribute("danhMuc", danhMuc);

		List<DanhMucSuKien> danhMucs = danhMucSuKienService.getAllOderByThuTu();
		model.addAttribute("danhMucs", danhMucs);

		return "admin/danhmucsukien/index";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("danhMuc") DanhMucSuKien danhMuc, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<DanhMucSuKien> danhMucs = danhMucSuKienService.getAllOderByThuTu();
			model.addAttribute("danhMucs", danhMucs);

			return "admin/danhmucsukien/index";
		}

		danhMucSuKienService.create(danhMuc);

		return "redirect:/admin/danhmucsukien";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		DanhMucSuKien danhMuc = danhMucSuKienService.getDanhMucSuKienById(id).orElse(null);
		model.addAttribute("danhMuc", danhMuc);
		return "admin/danhmucsukien/update";
	}

	@PostMapping("update")
	public String update(@Valid @ModelAttribute("danhMuc") DanhMucSuKien danhMuc, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin/danhmucbaiviet/update";
		}
		danhMucSuKienService.update(danhMuc);

		return "redirect:/admin/danhmucsukien";
	}

	@GetMapping("/up/{id}")
	public String up(@PathVariable("id") Integer id) {
		DanhMucSuKien danhMuc = danhMucSuKienService.getDanhMucSuKienById(id).orElse(null);
		danhMucSuKienService.up(danhMuc);
		return "redirect:/admin/danhmucsukien";
	}

	@GetMapping("/down/{id}")
	public String down(@PathVariable("id") Integer id) {
		DanhMucSuKien danhMuc = danhMucSuKienService.getDanhMucSuKienById(id).orElse(null);
		danhMucSuKienService.down(danhMuc);
		return "redirect:/admin/danhmucsukien";
	}
}
