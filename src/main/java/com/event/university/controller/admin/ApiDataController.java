package com.event.university.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.university.dto.LopDto;
import com.event.university.dto.NganhDto;
import com.event.university.service.LopService;
import com.event.university.service.NganhService;

@RestController
@RequestMapping("/api")
public class ApiDataController {

	@Autowired
	private NganhService nganhService;

	@Autowired
	private LopService lopService;

	@GetMapping("/nganhs/{khoaId}")
	public List<NganhDto> getNganhsByKhoa(@PathVariable Integer khoaId) {
		return nganhService.findByKhoaId(khoaId);
	}

	@GetMapping("/lops/{nganhId}")
	public List<LopDto> getLopsByNganh(@PathVariable Integer nganhId) {
		return lopService.findByNganhId(nganhId);
	}
}