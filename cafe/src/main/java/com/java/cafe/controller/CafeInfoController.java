package com.java.cafe.controller;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.service.CafeEachService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/{domain}/info")
public class CafeInfoController {

  private final CafeEachService cafeEachService;

  // 카페 기본정보 수정페이지
  @GetMapping
  public String baseInfo(@PathVariable("domain") String domain, Model model){
    cafeEachService.cafeInfo(domain, model);
    return "cafeMain/cafeManage";
  }

  // 카페 기본정보 수정
  @PatchMapping
  @ResponseBody
  public String baseInfoEdit(@PathVariable("domain") String domain, @ModelAttribute BoardDTO boardDTO){
    return cafeEachService.infoEdit(domain, boardDTO);
  }

  // 카페 삭제
  @DeleteMapping
  @ResponseBody
  public String delCafe(@PathVariable("domain") String domain) {
    try {
      Board board = cafeEachService.cafeBaseInfo(1, domain)
              .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다: domain=" + domain));
      board.setUseYN('N');
      cafeEachService.save(board);
      return "success";
    } catch (Exception e) {
      return "fail";
    }
  }

}
