package com.event.university.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.event.university.dto.LopDto;
import com.event.university.dto.NganhDto;
import com.event.university.entity.Lop;
import com.event.university.entity.Nganh;

@Component
public class NganhMapper {

	public LopDto toLopDto(Lop lop) {
		LopDto dto = new LopDto();
		dto.setId(lop.getId());
		dto.setTenLop(lop.getTenLop());
		dto.setKhoaSo(lop.getKhoaSo());
		return dto;
	}

	public NganhDto toNganhDto(Nganh nganh) {
		NganhDto dto = new NganhDto();
		dto.setId(nganh.getId());
		dto.setTenNganh(nganh.getTenNganh());
		dto.setMoTa(nganh.getMoTa());

		dto.setLop(nganh.getLop().stream().map(this::toLopDto).collect(Collectors.toSet()));
		return dto;
	}
}