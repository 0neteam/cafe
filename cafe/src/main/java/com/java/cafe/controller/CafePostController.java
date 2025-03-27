package com.java.cafe.controller;

import com.java.cafe.dto.MenuDTO;
import com.java.cafe.dto.PostDTO;
import com.java.cafe.dto.PostResDTO;
import com.java.cafe.service.CafeEachService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
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
    log.info("MENU : {}", menuList);
    model.addAttribute("menuList", menuList);
    model.addAttribute("domain", domain);
    return "cafeMain/write";
  }

  // 포스트 나 보기
  @GetMapping("/{no:[0-9]+}")
  public String read(@PathVariable("domain") String domain, @PathVariable("no") Integer no, Model model, HttpServletRequest req) {
    cafeEachService.read(domain, no, model, req);
    System.out.println("가나다라마바사"+domain+req+no);
    return "cafeMain/post";
  }

  // 포스트 저장
  @PutMapping
  @ResponseBody
  public PostResDTO writeCreate(@RequestBody PostDTO postDTO) {
    return cafeEachService.writeCreate(postDTO);
  }

  // 포스트 수정
  @PatchMapping("/{no:[0-9]+}")
  @ResponseBody
  public PostResDTO writeEdit(@PathVariable("no") Integer no, @RequestBody PostDTO postDTO, HttpServletRequest req) {
    return cafeEachService.writeEdit(no, postDTO, req);
  }

  // 포스트 삭제
  @DeleteMapping("/{no:[0-9]+}")
  @ResponseBody
  public PostResDTO writeDelete(@PathVariable("domain") String domain, @PathVariable("no") Integer no) {
    log.info("Post-No : {}", no);
    return cafeEachService.writeDel(no);
  }

}
