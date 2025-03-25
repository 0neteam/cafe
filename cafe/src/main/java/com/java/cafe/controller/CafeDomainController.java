package com.java.cafe.controller;

import com.java.cafe.dto.BoardDTO;
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
public class CafeDomainController {

  private final CafeEachService cafeEachService;

  @GetMapping
  public String route(@PathVariable String domain, Model model){
    BoardDTO boardDTO = cafeEachService.cafeInfo(1, domain);
    String checkDomain = boardDTO.getDomain();
    model.addAttribute("boardDTO", boardDTO);
    if (domain.equals(checkDomain)) {
      return "cafeMain/cafeHome";
    }
    return "redirect:/";
  }

}
