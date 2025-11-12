package com.event.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.event.university.entity.SuKien;
import com.event.university.service.SuKienService;

@Controller
public class DiemDanhController {

	@Autowired
	SuKienService suKienService;

	@GetMapping("/diemdanh/{id}")
	public String index(Model model, @PathVariable("id") Integer id) {
		SuKien suKien = suKienService.getById(id).orElse(null);
		model.addAttribute("suKien", suKien);
		return "event/diemdanh";
	}

}
