package com.event.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class XacThucController {

	@GetMapping("/dangnhap")
	public String Auth(Model model) {
		return "login";
	}

}
