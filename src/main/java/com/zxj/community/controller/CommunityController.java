package com.zxj.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {
    @GetMapping("/community")
    public String hello(){
        System.out.println("ok");
//        model.addAttribute("name",name);
            return "index";
    }
}
