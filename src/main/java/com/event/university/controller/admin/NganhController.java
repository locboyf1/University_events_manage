package com.event.university.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.university.entity.Khoa;
import com.event.university.entity.Nganh;
import com.event.university.service.KhoaService;
import com.event.university.service.NganhService;

@Controller
@RequestMapping("/admin/nganh")
public class NganhController {

	@Autowired
	NganhService nganhService;

	@Autowired
	KhoaService khoaService;

	@GetMapping("/{id}")
	public String index(@PathVariable("id") Integer khoaId, Model model) {
		List<Nganh> nganhs = nganhService.findByKhoaId(khoaId);
		Nganh nganh = new Nganh();
		Khoa khoa = khoaService.findById(khoaId);

		model.addAttribute("nganhs", nganhs);
		model.addAttribute("khoa", khoa);
		model.addAttribute("nganh", nganh);

		return "admin/nganh/index";

	}

	@PostMapping("/create")
	public String create(@ModelAttribute("nganh") Nganh nganh, @RequestParam Integer khoaId) {
		nganhService.create(nganh, khoaId);
		return "redirect:/admin/nganh/" + khoaId;
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		Nganh nganh = nganhService.findById(id);
		model.addAttribute("nganh", nganh);
		return "admin/nganh/update";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("nganh") Nganh nganh) {
		nganhService.update(nganh);
		Nganh nganhDB = nganhService.findById(nganh.getId());
		return "redirect:/admin/nganh/" + nganhDB.getKhoa().getId();
	}

}
