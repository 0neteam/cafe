package com.java.cafe.controller;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.service.CafeTempService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class CafeTempController {
    private final CafeTempService cafeTempService;

    @GetMapping("/{domain}")
    public String route(@PathVariable String domain, Model model){
        BoardDTO boardDTO = cafeTempService.cafeInfo(1, domain);
        int no = boardDTO.getNo();
        String checkDomain = boardDTO.getDomain();
        model.addAttribute("boardDTO", boardDTO);

        if (domain.equals(checkDomain)) {
            return "cafeMain/cafeHome";
        }
        return "cafe/home"; // 또는 예외 처리
    }
}
