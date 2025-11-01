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

import com.event.university.entity.Khoa;
import com.event.university.repository.KhoaRepository;
import com.event.university.service.KhoaService;

@Controller
@RequestMapping("/admin/khoa")
public class KhoaController {

	@Autowired
	KhoaRepository khoaRepository;

	@Autowired
	KhoaService khoaService;

	@GetMapping("")
	public String index(Model model) {
		List<Khoa> khoas = khoaService.getAll();
		model.addAttribute("khoas", khoas);

		Khoa khoa = new Khoa();
		model.addAttribute("khoa", khoa);
		return "admin/khoa/index";
	}

	@PostMapping("/create")
	public String create(Khoa khoa) {
		khoaRepository.save(khoa);
		return "redirect:/admin/khoa";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		Khoa khoa = khoaService.findById(id);
		model.addAttribute("khoa", khoa);
		return "admin/khoa/update";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("khoa") Khoa khoa) {
		khoaService.update(khoa);
		return "redirect:/admin/khoa";
	}
}
