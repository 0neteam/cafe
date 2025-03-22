package com.java.cafe.controller;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.service.CafeHomeService;
import com.java.cafe.service.CafeTempService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@AllArgsConstructor
public class CafeTempController {
    private final CafeTempService cafeTempService;
    private final CafeHomeService cafeHomeService;
    
    @GetMapping("/{domain}")
    public String route(@PathVariable String domain, Model model){
        BoardDTO boardDTO = cafeTempService.cafeInfo(1, domain);
        int no = boardDTO.getNo();
        String checkDomain = boardDTO.getDomain();
        model.addAttribute("boardDTO", boardDTO);

        if (domain.equals(checkDomain)) {
            return "cafeMain/cafeHome";
        }
        return "redirect:/cafe/home";
    }
    @GetMapping("/{domain}/setting/baseInfo")
    public String baseInfo(@PathVariable String domain, Model model){
        BoardDTO boardDTO = cafeTempService.cafeInfo(1, domain);
        model.addAttribute("boardDTO", boardDTO);
        return "cafeMain/cafeManage";
    }

    @PostMapping("/{domain}/setting/baseInfo")
    public String baseInfoEdit(@PathVariable String domain, @ModelAttribute BoardDTO boardDTO){
        Board board = cafeHomeService.cafeBaseInfo(1, domain)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다: domain=" + domain));

        board.setName(boardDTO.getName());
        board.setDomain(boardDTO.getDomain());
        board.setDescription(boardDTO.getDescription());

        cafeHomeService.save(board);

        return "redirect:/" + boardDTO.getDomain() + "/setting/baseInfo";
}

    @PostMapping("/{domain}/setting/delCafe")
    public String postMethodName(@PathVariable String domain) {
        
        return "redirect:/cafe/home";
    }
    

}
