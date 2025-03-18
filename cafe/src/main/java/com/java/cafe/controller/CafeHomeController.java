package com.java.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cafeHome")
public class CafeHomeController {

    @GetMapping("/")
    public String home(){
        return "";
    }
}
