package com.event.university.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

	@GetMapping("/dangnhap")
	public String Auth(Model model) {
		return "login";
	}

	@ResponseBody
	@GetMapping("/testpassword/{password}")
	public String testPasswordEncoder(@PathVariable String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = password;
		String encodedPassword = passwordEncoder.encode(rawPassword);

		return "Mật khẩu gốc: " + rawPassword + "Mật khẩu đã mã hóa: " + encodedPassword;
	}
}
