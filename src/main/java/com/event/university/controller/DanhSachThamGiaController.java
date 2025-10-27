package com.event.university.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.event.university.entity.DanhSachThamGia;
import com.event.university.entity.NguoiDung;
import com.event.university.entity.SuKien;
import com.event.university.service.DanhSachThamGiaService;
import com.event.university.service.NguoiDungService;
import com.event.university.service.SuKienService;

@Controller
@RequestMapping("/admin/events")
public class DanhSachThamGiaController {

	@Autowired
	private DanhSachThamGiaService danhSachThamGiaService;

	@Autowired
	private SuKienService suKienService;

	@Autowired
	private NguoiDungService nguoiDungService;

	// ✅ Trang chi tiết sự kiện
	@GetMapping("/{id}/detail")
	public String getDanhSachThamGia(@PathVariable("id") Integer suKienId, Model model) {
		Optional<SuKien> optionalSuKien = suKienService.getById(suKienId);
		if (optionalSuKien.isEmpty()) {
			return "redirect:/admin/events";
		}

		SuKien suKien = optionalSuKien.get();
		List<DanhSachThamGia> danhSachThamGia = danhSachThamGiaService.getBySuKienId(suKienId);
		List<NguoiDung> nguoiDungList = nguoiDungService.findSinhVienHoatDong(); // ✅ Thêm dòng này

		model.addAttribute("suKien", suKien);
		model.addAttribute("danhSachThamGia", danhSachThamGia);
		model.addAttribute("nguoiDungList", nguoiDungList); // ✅ Truyền sang giao diện
		model.addAttribute("showForm", true); // ✅ Cho phép hiển thị form

		return "admin/events/detail";
	}

	// ✅ Trang chỉnh sửa — hiển thị form thêm thành viên + danh sách người dùng
	@GetMapping("/update/{id}")
	public String showFormAddMember(@PathVariable("id") Integer suKienId, Model model) {
		Optional<SuKien> optionalSuKien = suKienService.getById(suKienId);
		if (optionalSuKien.isEmpty()) {
			return "redirect:/admin/events";
		}

		SuKien suKien = optionalSuKien.get();
		List<DanhSachThamGia> danhSachThamGia = danhSachThamGiaService.getBySuKienId(suKienId);
		List<NguoiDung> nguoiDungList = nguoiDungService.getAll(); // 🔹 Lấy toàn bộ người dùng

		model.addAttribute("suKien", suKien);
		model.addAttribute("danhSachThamGia", danhSachThamGia);
		model.addAttribute("nguoiDungList", nguoiDungList); // 🔹 Thêm vào model
		model.addAttribute("showForm", true);

		return "admin/events/detail";
	}

	// ✅ Xử lý thêm thành viên vào sự kiện
	@PostMapping("/add-member")
	public String addMemberToEvent(@RequestParam Integer suKienId, @RequestParam String taiKhoan,
			RedirectAttributes redirectAttributes) {

		try {
			Optional<NguoiDung> nguoiDungOpt = nguoiDungService.findByTaiKhoan(taiKhoan);
			if (nguoiDungOpt.isEmpty()) {
				redirectAttributes.addFlashAttribute("error", "Không tìm thấy người dùng với tài khoản: " + taiKhoan);
				return "redirect:/admin/events/update/" + suKienId;
			}

			NguoiDung nguoiDung = nguoiDungOpt.get();
			danhSachThamGiaService.addNguoiDungVaoSuKien(nguoiDung, suKienId);

			redirectAttributes.addFlashAttribute("success", "Đã thêm thành viên thành công!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}

		return "redirect:/admin/events/update/" + suKienId;
	}

	@PostMapping("/remove-member")
	public String removeMemberFromEvent(@RequestParam Integer suKienId, @RequestParam Integer nguoiDungId) {

		danhSachThamGiaService.removeNguoiDungKhoiSuKien(nguoiDungId, suKienId);

		return "redirect:/admin/events/" + suKienId + "/detail";
	}

}
