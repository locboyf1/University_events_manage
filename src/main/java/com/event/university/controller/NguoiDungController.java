package com.event.university.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.event.university.entity.NguoiDung;
import com.event.university.repository.NguoiDungRepository;
import com.event.university.service.NguoiDungService;

@Controller
@RequestMapping("/thongtin")
public class NguoiDungController {

	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	@Autowired
	private NguoiDungService nguoiDungService;

	@GetMapping()
	public String thongTin(Model model, @AuthenticationPrincipal NguoiDung nguoiDung) {
		model.addAttribute("nguoiDung", nguoiDung);
		return "User/user";
	}

	@PostMapping("/doianhdaidien")
	public String doianhdaidien(@AuthenticationPrincipal NguoiDung nguoiDung, MultipartFile fileAnh)
			throws IOException {
		nguoiDungService.changeAvatar(nguoiDung, fileAnh);
		return "redirect:/thongtin";
	}

	@PostMapping("/capnhatthongtin")
	public String capnhatthongtin(@ModelAttribute NguoiDung nguoiDung) {
		nguoiDungService.changeEmailAndPhone(nguoiDung);
		return "redirect:/thongtin";
	}
}