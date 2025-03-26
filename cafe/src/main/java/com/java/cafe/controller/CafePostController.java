package com.java.cafe.controller;

import com.java.cafe.dto.MenuDTO;
import com.java.cafe.dto.PostDTO;
import com.java.cafe.service.CafeEachService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/{domain}/post")
public class CafePostController {

  private final CafeEachService cafeEachService;

  // 포스트 작성 화면
  @GetMapping
  public String write(@PathVariable("domain") String domain, Model model) {
    List<MenuDTO> menuList = cafeEachService.getMenuList(domain);
    model.addAttribute("menuList", menuList);
    model.addAttribute("domain", domain);
    return "cafeMain/write";
  }

  // 포스트 나 보기
  @GetMapping("/{no:[0-9]+}")
  public String read(@PathVariable("domain") String domain, @PathVariable("no") Integer no, Model model) {
    log.info("domain : {}, Post-No : {}", domain, no);
    return "cafeMain/read";
  }

  // 포스트 저장
  @PutMapping
  @ResponseBody
  public String writeCreate(@ModelAttribute PostDTO postDTO) {
      return "redirect:/";
  }

  // 포스트 수정
  @PatchMapping
  @ResponseBody
  public String writeEdit(@ModelAttribute PostDTO postDTO) {
      return "redirect:/";
  }

  // 포스트 삭제
  @DeleteMapping("/{no:[0-9]+}")
  @ResponseBody
  public String writeDelete(@PathVariable("domain") String domain, @PathVariable("no") Integer no) {
    log.info("domain : {}, Post-No : {}", domain, no);
    return "redirect:/";
  }

}
