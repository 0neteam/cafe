package com.java.cafe.controller;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.dto.MenuDTO;
import com.java.cafe.dto.PostDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.entity.Menu;
import com.java.cafe.repository.MenuRepository;
import com.java.cafe.service.CafeEachService;
import com.java.cafe.service.CafeHomeService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Controller
//@AllArgsConstructor
//@RequestMapping("/{domain}")
public class CafeEachController {

//    private final CafeEachService cafeEachService;
//    private final CafeHomeService cafeHomeService;

    // 개별 카페 메인페이지
//    @GetMapping
//    public String route(@PathVariable String domain, Model model){
//        BoardDTO boardDTO = cafeEachService.cafeInfo(1, domain);
//        String checkDomain = boardDTO.getDomain();
//        model.addAttribute("boardDTO", boardDTO);
//
//        if (domain.equals(checkDomain)) {
//            return "cafeMain/cafeHome";
//        }
//        return "redirect:/cafe/home";
//    }

//    // 카페 기본정보 수정페이지
//    @GetMapping("/setting/baseInfo")
//    public String baseInfo(@PathVariable String domain, Model model){
//        BoardDTO boardDTO = cafeEachService.cafeInfo(1, domain);
//        model.addAttribute("boardDTO", boardDTO);
//        return "cafeMain/cafeManage";
//    }
//
//    // 카페 기본정보 수정
//    @PostMapping("/setting/baseInfo")
//    public String baseInfoEdit(@PathVariable String domain, @ModelAttribute BoardDTO boardDTO){
//        Board board = cafeHomeService.cafeBaseInfo(1, domain)
//        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다: domain=" + domain));
//
//        board.setName(boardDTO.getName());
//        board.setDomain(boardDTO.getDomain());
//        board.setDescription(boardDTO.getDescription());
//
//        cafeHomeService.save(board);
//
//        return "redirect:/" + boardDTO.getDomain() + "/setting/baseInfo";
//}


//    @GetMapping("/setting/menu")
//    public String getMenuList(@PathVariable String domain, Model model) {
//        List<MenuDTO> menuList = cafeEachService.getMenuList(domain);
//        model.addAttribute("menuList", menuList);
//        model.addAttribute("domain", domain);
//        return "cafeMain/menuManage";
//    }
//
//    // 메뉴 추가
//    @PostMapping("/menu/create")
//    @ResponseBody
//    public String createMenu(@PathVariable String domain, @RequestBody MenuDTO menuDTO) {
//        if (cafeEachService.createMenu(domain, menuDTO) != null){
//            return "success";
//        } else{
//            return "fail";
//        }
//    }
//
//     // 메뉴 수정
//    @PostMapping("/menu/edit")
//    @ResponseBody
//    public String editMenu(@PathVariable String domain, @RequestBody MenuDTO menuDTO) {
//        if (cafeEachService.editMenu(domain, menuDTO) != null){
//            return "success";
//        } else{
//            return "fail";
//        }
//    }
//
//    // 메뉴 삭제
//    @PostMapping("/menu/delete")
//    @ResponseBody
//    public String deleteMenu(@PathVariable String domain, @RequestBody MenuDTO menuDTO) {
//        if (cafeEachService.deleteMenu(domain, menuDTO) != null){
//            return "success";
//        } else {
//            return "fail";
//        }
//    }

    // 포스트 작성 화면
//    @GetMapping("/post/write")
//    public String write(@PathVariable String domain, Model model) {
//        List<MenuDTO> menuList = cafeEachService.getMenuPost(domain);
//        model.addAttribute("menuList", menuList);
//        model.addAttribute("domain", domain);
//        return "cafeMain/write";
//    }

    // 포스트 저장
//    @PostMapping("/post/write")
//    public String writeCreate(@ModelAttribute PostDTO postDTO) {
//        return "redirect:/";
//    }

     // 포스트 수정
//    @PostMapping("/post/edit")
//    public String writeEdit(@ModelAttribute PostDTO postDTO) {
//        return "redirect:/";
//    }
//
     // 포스트 삭제
//    @PostMapping("/post/delete")
//    public String writeDelete(@ModelAttribute PostDTO postDTO) {
//        return "redirect:/";
//    }


}
