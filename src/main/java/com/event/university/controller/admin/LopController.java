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
import org.springframework.web.bind.annotation.RequestParam;

import com.event.university.entity.Lop;
import com.event.university.entity.Nganh;
import com.event.university.repository.LopRepository;
import com.event.university.service.LopService;
import com.event.university.service.NganhService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/lop")
public class LopController {

	@Autowired
	LopService lopService;

	@Autowired
	LopRepository lopRepository;

	@Autowired
	NganhService nganhService;

	@GetMapping("/{id}")
	public String index(@PathVariable("id") Integer nganhId, Model model) {
		List<Lop> lops = lopService.findByNganhId(nganhId);
		model.addAttribute("lops", lops);

		Nganh nganh = nganhService.findById(nganhId);
		model.addAttribute("nganh", nganh);

		Lop lop = new Lop();
		model.addAttribute("lop", lop);
		return "admin/lop/index";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("lop") Lop lop, BindingResult result,
			@RequestParam("nganhId") Integer nganhId, Model model) {

		if (result.hasErrors()) {
			List<Lop> lops = lopService.findByNganhId(nganhId);
			model.addAttribute("lops", lops);

			Nganh nganh = nganhService.findById(nganhId);
			model.addAttribute("nganh", nganh);

			return "admin/lop/index";
		}
		lopService.create(lop, nganhId);
		return "redirect:/admin/lop/" + nganhId;
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		Lop lop = lopService.findById(id);
		model.addAttribute("lop", lop);
		return "admin/lop/update";
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("lop") Lop lop, BindingResult result, Model model) {
		if (result.hasErrors()) {
			Lop lopDB = lopService.findById(lop.getId());
			lop.setNganh(lopDB.getNganh());
			return "admin/lop/update";
		}

		lopService.update(lop);
		Lop lopDB = lopService.findById(lop.getId());
		return "redirect:/admin/lop/" + lopDB.getNganh().getId();
	}
}
