package com.java.cafe.controller;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.dto.MenuDTO;
import com.java.cafe.dto.PostDTO;
import com.java.cafe.entity.Menu;
import com.java.cafe.entity.Post;
import com.java.cafe.service.CafeEachService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/{domain}")
public class CafeDomainController {

  private final CafeEachService cafeEachService;

  @GetMapping
  public String route(@PathVariable String domain, Model model){
    BoardDTO boardDTO = cafeEachService.cafeInfo(1, domain);
    String checkDomain = boardDTO.getDomain();
    model.addAttribute("boardDTO", boardDTO);
    List<MenuDTO> menuList = cafeEachService.getMenuList(domain);
    model.addAttribute("menuList", menuList);
    if (domain.equals(checkDomain)) {
      return "cafeMain/cafeHome";
    }
    return "redirect:/";
  }


  @GetMapping("/{no:[0-9]+}")
  public String menuMain(@PathVariable(name = "domain") String domain, @PathVariable(name = "no") Integer no, Model model) {
    BoardDTO boardDTO = cafeEachService.cafeInfo(1, domain);
    // String checkDomain = boardDTO.getDomain();
    model.addAttribute("boardDTO", boardDTO);
    List<MenuDTO> menuList = cafeEachService.getMenuList(domain);
    model.addAttribute("menuList", menuList);
    Menu menu = cafeEachService.getMenu(no);
    model.addAttribute("menu", menu);
    List<Post> postList = cafeEachService.getPostList(no);
    model.addAttribute("postList", postList);
    System.out.println("--------------------------------여기부터시작"+postList);
    return "cafeMain/menuMain";
  }

}
