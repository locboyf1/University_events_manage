package com.event.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
		List<SuKien> suKiens = suKienService.getAllSuKien();
		List<DanhMucSuKien> danhMucSuKiens = danhMucSuKienService.getAllDanhMucSuKien();
		model.addAttribute("suKiens", suKiens);
		model.addAttribute("danhMucSuKiens", danhMucSuKiens);
		return "event/index";
	}
}
