package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String Home() {
        return "home";
    }
    //Spring container를 찾고 여기에 없으면 static을 찾기 때문에 home.html을 실행함.
}
