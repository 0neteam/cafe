package com.java.cafe.controller;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.entity.User;
import com.java.cafe.repository.UserRepository;
import com.java.cafe.service.CafeHomeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    public String createCafe(@ModelAttribute BoardDTO boardDTO, HttpServletRequest req) {
        return cafeHomeService.save(boardDTO, req);
    }

}
