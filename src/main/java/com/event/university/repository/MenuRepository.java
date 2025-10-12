package com.event.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.university.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByMenuChaIsNullAndHienTrueOrderByThuTuAsc();

    List<Menu> findByMenuChaAndHienTrueOrderByThuTuAsc(Menu menuCha);
}