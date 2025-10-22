package com.event.university.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.event.university.entity.NguoiDung;
import com.event.university.repository.NguoiDungRepository;
import com.event.university.service.UpdatefileService;


@Controller
public class User {
	@GetMapping("/thongtin")
	public String thongTin(Model model, @AuthenticationPrincipal NguoiDung nguoiDung) {
		model.addAttribute("user", nguoiDung);
		return "User/user";
	}
	
	@Autowired
	private NguoiDungRepository userRepository; 
	 
	@Autowired
	private UpdatefileService fileStorageService;
	
	@PostMapping("/upload-avatar")
	public String uploadAvatar(@RequestParam("file") MultipartFile file,
	                           Principal principal,
	                           RedirectAttributes redirectAttributes) {
	    try {
	        Optional<NguoiDung> optionalUser = userRepository.findByTaiKhoan(principal.getName());

	        if (optionalUser.isPresent()) {
	            NguoiDung user = optionalUser.get();
	            String filePath = fileStorageService.saveFile(file);
	            user.setAnh(filePath);  // Lưu đường dẫn "/assets/images/profile/..."
	            userRepository.save(user);

	            redirectAttributes.addFlashAttribute("success", "Cập nhật ảnh thành công!");
	        } else {
	            redirectAttributes.addFlashAttribute("error", "Không tìm thấy người dùng!");
	        }

	    } catch (IOException e) {
	        redirectAttributes.addFlashAttribute("error", "Không thể lưu ảnh!");
	    }

	    return "redirect:/thongtin";
	}
}