package com.java.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CafeHomeController {

    @GetMapping("/")
    public String home(){
        return "cafeHome/home";
    }
}
