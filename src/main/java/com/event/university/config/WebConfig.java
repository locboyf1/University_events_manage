package com.event.university.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.event.university.converter.LopConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private LopConverter lopConverter;

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(lopConverter);
	}

}