package com.java.cafe.controller;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.service.CafeHomeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/")
public class CafeHomeController {
    private final CafeHomeService cafeHomeService;

    @GetMapping
    public String home(@RequestParam(name = "keyword", required = false) String keyword, Model model){
        cafeHomeService.searchCafeList(keyword, model);
        return "cafeHome/home";
    }

    @PostMapping
    public String create(){
        return "cafeHome/cafeCreate";
    }

    @ResponseBody
    @PutMapping
    public String createCafe(@ModelAttribute BoardDTO boardDTO) {
        return cafeHomeService.save(boardDTO);
    }

}
