package com.event.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.university.entity.Menu;
import com.event.university.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;

	public List<Menu> getTopLevelMenus() {

		return menuRepository.findByMenuChaIsNullAndHienTrueOrderByThuTuAsc();
	}

}