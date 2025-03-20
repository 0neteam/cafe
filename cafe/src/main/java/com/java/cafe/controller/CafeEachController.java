package com.java.cafe.controller;

import com.java.cafe.dto.MenuDTO;
import com.java.cafe.repository.MenuRepository;
import com.java.cafe.service.CafeEachService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/cafe")
public class CafeEachController {

    private final CafeEachService cafeEachService;

    @GetMapping("/menu")
    public String getMenuList(Model model) {
        List<MenuDTO> menuList = cafeEachService.getMenuList(2);
        model.addAttribute("menuList", menuList);
        return "/cafeMain/menuManage";
    }

    // 메뉴 추가
    @PostMapping("/menu/create")
    @ResponseBody
    public String createMenu(@RequestBody MenuDTO menuDTO) {
        cafeEachService.createMenu(menuDTO);
        return "success";
    }

    // 메뉴 수정
//    @PostMapping("/menu/edit")
//    @ResponseBody
//    public String editMenu(@RequestBody MenuDTO menuDTO) {
//        cafeEachService.editMenu(menuDTO);
//        return "redirect:/menu";
//    }

    // 메뉴 삭제
    @PostMapping("/menu/delete")
    @ResponseBody
    public String deleteMenu(@RequestBody MenuDTO menuDTO) {
        if (cafeEachService.deleteMenu(menuDTO) != null){
            return "success";
        } else{
            return "fail";
        }
    }

    // 포스트 조회
//    @GetMapping("/post/detail")
//    public String write(Model model) {
//        return "cafeMain/write";
//    }
//
     // 포스트 저장
//    @PostMapping("/post/create")
//    public String writeCreate(@ModelAttribute PostDTO postDTO) {
//        return "redirect:/";
//    }
//
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
