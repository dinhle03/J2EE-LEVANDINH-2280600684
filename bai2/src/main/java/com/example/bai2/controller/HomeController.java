package com.example.bai2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/home")
    public  String home(Model model){
        model.addAttribute("message", "Xin chao");
        return "index";
    }
//    @GetMapping("/about")
//    public String about(@RequestParam String title)
//    public String Index(){
//        return "index";
//    }
}
