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
import org.springframework.web.bind.annotation.RequestParam;

import com.event.university.entity.Khoa;
import com.event.university.entity.Lop;
import com.event.university.entity.Nganh;
import com.event.university.entity.NguoiDung;
import com.event.university.service.KhoaService;
import com.event.university.service.NguoiDungService;
import com.event.university.service.VaiTroService;

@Controller
@RequestMapping("/admin/users")
public class UsersController {

	@Autowired
	private NguoiDungService nguoiDungService;
	@Autowired
	private VaiTroService vaiTroService;

	@Autowired
	private KhoaService khoaService;

	@GetMapping({ "/", "" })
	public String manageUsers(Model model) {
		List<NguoiDung> nguoiDungs = nguoiDungService.getAll();
		model.addAttribute("nguoiDungs", nguoiDungs);
		return "admin/users/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		NguoiDung nguoiDung = new NguoiDung();
		nguoiDung.setVaiTro(vaiTroService.findByAlias("sinhvien"));
		model.addAttribute("nguoiDung", nguoiDung);
		model.addAttribute("vaiTros", vaiTroService.getAll());
		model.addAttribute("khoas", khoaService.getAll());
		return "admin/users/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute NguoiDung nguoiDung) {
		nguoiDungService.create(nguoiDung);
		return "redirect:/admin/users/";
	}

	@GetMapping("/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		NguoiDung nguoiDung = nguoiDungService.getById(id);
		model.addAttribute("nguoiDung", nguoiDung);
		model.addAttribute("vaiTros", vaiTroService.getAll());
		model.addAttribute("khoas", khoaService.getAll());

		Integer lopId = null;
		Integer nganhId = null;
		Integer khoaId = null;

		if (nguoiDung.getLop() != null) {
			Lop lop = nguoiDung.getLop();
			lopId = lop.getId();

			Nganh nganh = lop.getNganh();
			nganhId = nganh.getId();

			Khoa khoa = nganh.getKhoa();
			khoaId = khoa.getId();

		}

		model.addAttribute("khoaId", khoaId);
		model.addAttribute("nganhId", nganhId);
		model.addAttribute("lopId", lopId);

		return "admin/users/update";

	}

	@PostMapping("/update")
	public String update(@ModelAttribute NguoiDung nguoiDung) {
		nguoiDungService.update(nguoiDung);
		return "redirect:/admin/users/";
	}

	@PostMapping("/lock")
	public String lock(@RequestParam Integer id) {
		nguoiDungService.lockUnlock(id);
		return "redirect:/admin/users/";
	}
}