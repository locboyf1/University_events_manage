package com.event.university.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/events")
public class EventsController {

	@GetMapping({ "/", "" })
	public String manageEvents(Model model) {

		return "admin/events/index";
	}
}
