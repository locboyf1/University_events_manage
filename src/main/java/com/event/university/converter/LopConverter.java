package com.event.university.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.event.university.entity.Lop;
import com.event.university.repository.LopRepository;

@Component
public class LopConverter implements Converter<String, Lop> {

	@Autowired
	private LopRepository lopRepository;

	@Override
	public Lop convert(String id) {
		if (id == null || id.isEmpty()) {
			return null;
		}

		Integer lopId = Integer.parseInt(id);
		return lopRepository.findById(lopId).orElse(null);

	}
}