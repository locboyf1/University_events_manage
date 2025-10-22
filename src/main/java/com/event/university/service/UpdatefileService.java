package com.event.university.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UpdatefileService {
	 private final Path root = Paths.get("src/main/resources/static/assets/images/profile");

	    public String saveFile(MultipartFile file) throws IOException {
	        if (!Files.exists(root)) {
	            Files.createDirectories(root);
	        }
	        String fileName =file.getOriginalFilename();
	        Files.copy(file.getInputStream(), root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
	        return "/assets/images/profile/" + fileName;
	    }
	
}
