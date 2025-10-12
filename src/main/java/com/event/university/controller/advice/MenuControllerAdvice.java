package com.event.university.controller.advice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.event.university.entity.Menu;
import com.event.university.service.MenuService;

@ControllerAdvice
public class MenuControllerAdvice {

    @Autowired
    private MenuService menuService;

    @ModelAttribute("topMenus")
    public List<Menu> addGlobalMenusToModel() {
        return menuService.getTopLevelMenus();
    }
}