package com.java.cafe.controller;

import com.java.cafe.service.CafeEachService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/{domain}")
public class CafeEachController {

  private final CafeEachService cafeEachService;

  // 카페별 메인화면 보여주기
  @GetMapping
  public String route(@PathVariable String domain, Model model){
    cafeEachService.cafeInfo(domain, model);
    cafeEachService.getMenuList(domain, model);
    if (cafeEachService.cafeInfo(domain, model)=="success") {
      return "cafeMain/cafeHome";
    }
    return "redirect:/";
  }

  //카페별 메뉴 보여주기
  @GetMapping("/{no:[0-9]+}")
  public String menuMain(@PathVariable(name = "domain") String domain, @PathVariable(name = "no") Integer no, Model model) {
    cafeEachService.cafeInfo(domain, model);
    cafeEachService.getMenuList(domain, model);
    cafeEachService.getMenu(no, model);
    cafeEachService.getPostList(no, model);
    return "cafeMain/menuMain";
  }

}
