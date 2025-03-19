package com.java.cafe.controller;

import com.java.cafe.dto.MenuDTO;
import com.java.cafe.dto.PostDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.entity.Menu;
import com.java.cafe.entity.Post;
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
public class CafeEachController {

    private final CafeEachService cafeEachService;
    private final MenuRepository menuRepository;

    @GetMapping("/main")
    public String main(Model model) {
        List<MenuDTO> menuList = new ArrayList<>();
//        menuList.add(new MenuDTO(1, 1, 1, 0, "카페 A", 0, 'Y'));
//        menuList.add(new MenuDTO(2, 2, 2, 0, "카페 B", 0, 'N'));
        model.addAttribute("menuList", menuList);
        return "cafeMain/cafeHome";
    }

    @GetMapping("/menu")
    public String getMenuList(Model model) {
        List<MenuDTO> menuList = cafeEachService.getMenuList(2); // 서비스에서 메뉴 목록을 가져옴
        model.addAttribute("menuList", menuList); // 뷰에 메뉴 목록 전달
        return "/cafeMain/menuManage";
    }

    // 메뉴 추가
    @PostMapping("/menu/create")
    public String createMenu(@ModelAttribute MenuDTO menuDTO) {
        cafeEachService.createMenu(menuDTO);
        return "redirect:/menu";
    }

    // 메뉴 수정
    @PostMapping("/menu/edit")
    public String editMenu(@ModelAttribute MenuDTO menuDTO) {
        cafeEachService.editMenu(menuDTO);
        return "redirect:/menu";
    }

    // 메뉴 삭제
    @PostMapping("/menu/delete")
    @ResponseBody // 이거 추가!
    public String deleteMenu(@RequestBody MenuDTO menuDTO) {
        cafeEachService.deleteMenu(menuDTO);
        System.out.println(menuDTO);
        return "success"; // redirect가 아닌 성공 메시지 반환
    }

    @GetMapping("/write")
    public String write(Model model) {
        return "cafeMain/write";
    }

    @PostMapping("/write/save")
    public String writeSave(@ModelAttribute PostDTO postDTO) {
        Post post = Post.builder()
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .regUserNo(postDTO.getRegUserNo())
                .build();

        System.out.println(post);
        return "redirect:/";
    }

    @PostMapping("/write/edit")
    public String writeEdit(@ModelAttribute PostDTO postDTO) {
        Post post = Post.builder()
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .regUserNo(postDTO.getRegUserNo())
                .build();

        System.out.println(post);
        return "redirect:/";
    }

    @PostMapping("/write/delete")
    public String writeDelete(@ModelAttribute PostDTO postDTO) {
        Post post = Post.builder()
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .regUserNo(postDTO.getRegUserNo())
                .build();

        System.out.println(post);
        return "redirect:/";
    }


}
