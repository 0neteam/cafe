package com.java.cafe.controller;

import com.java.cafe.dto.BoardDTO;
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
    cafeEachService.getMenuList(domain, model);
    return "cafeMain/write";
  }

  // 포스트 나 보기
  @GetMapping("/{no:[0-9]+}")
  public String read(@PathVariable("domain") String domain, @PathVariable("no") Integer no, Model model, HttpServletRequest req) {
    cafeEachService.cafeInfo(domain, model);
    cafeEachService.getMenuList(domain, model);
    cafeEachService.read(domain, no, model, req);
    return "cafeMain/post";
  }

  // 포스트 저장
  @PutMapping
  @ResponseBody
  public PostResDTO writeCreate(@RequestBody PostDTO postDTO, HttpServletRequest req) {
    return cafeEachService.writeCreate(postDTO, req);
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
  public PostResDTO writeDelete(@PathVariable("no") Integer no) {
    return cafeEachService.writeDel(no);
  }

}