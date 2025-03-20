package com.java.cafe.service;

import com.java.cafe.dto.MenuDTO;

import java.util.List;

public interface CafeEachService {

    public List<MenuDTO> getMenuList(String domain);
    public MenuDTO createMenu(String domain, MenuDTO menuDTO);
    public MenuDTO editMenu(String domain, MenuDTO menuDTO);
    public String deleteMenu(String domain, MenuDTO menuDTO);

}
