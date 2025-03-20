package com.java.cafe.service;

import com.java.cafe.dto.MenuDTO;

import java.util.List;

public interface CafeEachService {

    public List<MenuDTO> getMenuList(Integer boardNo);
    public MenuDTO createMenu(MenuDTO menuDTO);
    public MenuDTO editMenu(MenuDTO menuDTO);
    public String deleteMenu(MenuDTO menuDTO);

}
