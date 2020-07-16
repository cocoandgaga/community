package com.zxj.community.controller;

import com.zxj.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/login")
    public String index( ){
        return "login";
    }
}



