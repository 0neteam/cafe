package com.java.cafe.controller;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.dto.MenuDTO;
import com.java.cafe.service.CafeEachService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/{domain}/menu")
public class CafeMenuController {

  private final CafeEachService cafeEachService;

  @GetMapping
  public String getMenuList(@PathVariable String domain, Model model) {
    cafeEachService.cafeInfo(domain, model);
    cafeEachService.getMenuList(domain, model);
    return "cafeMain/menuManage";
  }

  // 메뉴 추가
  @PutMapping
  @ResponseBody
  public String createMenu(@PathVariable String domain, @RequestBody MenuDTO menuDTO) {
    return cafeEachService.createMenu(domain, menuDTO);
  }

  // 메뉴 수정
  @PatchMapping
  @ResponseBody
  public String editMenu(@PathVariable String domain, @RequestBody MenuDTO menuDTO) {
    return cafeEachService.editMenu(domain, menuDTO);
  }

  // 메뉴 삭제
  @DeleteMapping("/{no:[0-9]+}")
  @ResponseBody
  public String deleteMenu(@PathVariable String domain, @PathVariable("no") Integer no) {
    return cafeEachService.deleteMenu(domain, no);
  }

}
