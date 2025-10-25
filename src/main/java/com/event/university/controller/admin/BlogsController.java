package com.event.university.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/blogs")
@Controller
public class BlogsController {
	@GetMapping({ "", "/" })
	public String index(Model model) {
		return "admin/blogs/index";
	}
}
