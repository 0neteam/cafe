package com.java.cafe.controller;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.service.CafeHomeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/cafe")
public class CafeHomeController {
    private final CafeHomeService cafeHomeService;

    @GetMapping("/home")
    public String home(Model model){
        List<Board> cafeList = cafeHomeService.homeCafeList(1,'Y');
        model.addAttribute("cafeList", cafeList);
        return "cafeHome/home";
    }

    @GetMapping("")
    public String cafedomain(){
        return "redirect:/cafe/home";
    }

    @GetMapping("/home/create")
    public String create(){
        return "cafeHome/cafeCreate";
    }

    @PostMapping("/home/create")
    public String createCafe(@ModelAttribute BoardDTO boardDTO) {
        Board board = Board.builder()
                .regUserNo(1)
                .type(1)
                .name(boardDTO.getName())
                .domain(boardDTO.getDomain())
                .description(boardDTO.getDescription())
                .regDate(LocalDate.now())
                .useYN('Y')
                .build();
        cafeHomeService.save(board);

        return "redirect:/cafe/home";
    }

    @GetMapping("/home/search")
    public String SearchCafe(@RequestParam String keyward, Model model){
        List<Board> cafeList = cafeHomeService.searchCafeList(keyward);
        model.addAttribute("cafeList", cafeList);
        model.addAttribute("keyward", keyward);
        System.out.println("keyward : "+keyward);
        return "cafeHome/cafeSearch";
    }
}
