package com.event.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.event.university.entity.DanhMucSuKien;
import com.event.university.entity.SuKien;
import com.event.university.service.DanhMucSuKienService;
import com.event.university.service.SuKienService;

@Controller
public class EventController {
	@Autowired
	private SuKienService suKienService;
	
	@Autowired
	private DanhMucSuKienService danhMucSuKienService;
	
	@GetMapping("/events")
	public String  index(Model model) {	
		List<SuKien> suKiens = suKienService.getDisplaySorted();
		List<DanhMucSuKien> danhMucSuKiens = danhMucSuKienService.getAllDanhMucSuKien();
		model.addAttribute("suKiens", suKiens);
		model.addAttribute("danhMucSuKiens", danhMucSuKiens);
		return "event/index";
	}
	
	@GetMapping("/events/{id}/{biDanh}.html")
	public String detail(@PathVariable Long id, @PathVariable String biDanh, Model model) {
		SuKien suKien = suKienService.getById(id);
		if (suKien == null) {
			return "event/index";
		}
		
		model.addAttribute("suKien",suKien);
		
		return "event/detail";
	}
}
