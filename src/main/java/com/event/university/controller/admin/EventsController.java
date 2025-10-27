package com.event.university.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.university.entity.DanhMucSuKien;
import com.event.university.entity.SuKien;
import com.event.university.service.DanhMucSuKienService;
import com.event.university.service.SuKienService;

@Controller
@RequestMapping("/admin/events")
public class EventsController {
	@Autowired
	private SuKienService suKienService;

	@Autowired
	private DanhMucSuKienService danhMucSuKienService;
	@GetMapping({ "/", "" })
	public String manageEvents(Model model) {

		List<SuKien> suKiens = suKienService.getDisplaySorted();
		List<DanhMucSuKien> danhMucSuKiens = danhMucSuKienService.getAllDanhMucSuKien();
		model.addAttribute("suKiens", suKiens);
		model.addAttribute("danhMucSuKiens", danhMucSuKiens);
		return "admin/events/index";
	}
	@GetMapping("/{id}")
	public String eventDetail(@PathVariable Integer id, Model model) {
	    SuKien suKien = suKienService.getById(id).orElse(null);
	    if (suKien == null) {
	        return "redirect:/admin/events"; // nếu không tìm thấy thì quay lại danh sách
	    }
	    model.addAttribute("suKien", suKien);

	    // (Tạm thời thêm dữ liệu tĩnh cho danh sách thành viên)
	    List<String> thanhVienThamGia = List.of("Nguyễn Văn A", "Trần Thị B", "Phạm Văn C");
	    model.addAttribute("thanhVienThamGia", thanhVienThamGia);

	    return "admin/events/detail";
	}

}
