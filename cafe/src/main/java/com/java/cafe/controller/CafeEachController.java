package com.java.cafe.controller;

import com.java.cafe.dto.MenuDTO;
import com.java.cafe.dto.PostDTO;
import com.java.cafe.entity.Menu;
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
//@RequestMapping("/cafe")
public class CafeEachController {

    private final CafeEachService cafeEachService;

    @GetMapping("/{domain}/setting/menu")
    public String getMenuList(@PathVariable String domain, Model model) {
        List<MenuDTO> menuList = cafeEachService.getMenuList(domain);
        model.addAttribute("menuList", menuList);
        model.addAttribute("domain", domain);
        return "cafeMain/menuManage";
    }

    // 메뉴 추가
    @PostMapping("/{domain}/menu/create")
    @ResponseBody
    public String createMenu(@PathVariable String domain, @RequestBody MenuDTO menuDTO) {
        if (cafeEachService.createMenu(domain, menuDTO) != null){
            return "success";
        } else{
            return "fail";
        }
    }

     // 메뉴 수정
    @PostMapping("/{domain}/menu/edit")
    @ResponseBody
    public String editMenu(@PathVariable String domain, @RequestBody MenuDTO menuDTO) {
        if (cafeEachService.editMenu(domain, menuDTO) != null){
            return "success";
        } else{
            return "fail";
        }
    }

    // 메뉴 삭제
    @PostMapping("/{domain}/menu/delete")
    @ResponseBody
    public String deleteMenu(@PathVariable String domain, @RequestBody MenuDTO menuDTO) {
        if (cafeEachService.deleteMenu(domain, menuDTO) != null){
            return "success";
        } else {
            return "fail";
        }
    }

    // 각 도메인별 메뉴 메인 화면
//    @GetMapping("{domain}/")
//    @ResponseBody
//    public String getMenuList(@PathVariable String domain, @PathVariable String menu, Model model) {
//        List<MenuDTO> menuList = cafeEachService.getMenuList(domain);
//        model.addAttribute("menuList", menuList);
//        model.addAttribute("domain", domain);
//        return "cafeMain/menuMain";
//    }

    // 포스트 작성 화면
    @GetMapping("/{domain}/post/write")
    public String write(@PathVariable String domain, Model model) {
        List<MenuDTO> menuList = cafeEachService.getMenuPost(domain);
        model.addAttribute("menuList", menuList);
        model.addAttribute("domain", domain);
        return "cafeMain/write";
    }

    // 포스트 저장
    @PostMapping("/post/create")
    public String writeCreate(@ModelAttribute PostDTO postDTO) {
        return "redirect:/";
    }

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
