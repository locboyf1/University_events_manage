package com.event.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.event.university.entity.SuKien;
import com.event.university.service.SuKienService;

@Controller
public class TrangChuController {

	@Autowired
	private SuKienService suKienService;

	@GetMapping({ "/", "/trangchu" })
	public String home(Model model) {
		List<SuKien> suKiens = suKienService.getDisplaySorted();
		model.addAttribute("suKiens", suKiens);
		return "home";
	}
}