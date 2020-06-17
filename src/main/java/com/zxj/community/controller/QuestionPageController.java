package com.zxj.community.controller;

import com.zxj.community.dto.QuestionDTO;
import com.zxj.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionPageController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questionPage/{id}")
    public String questionPage(@PathVariable(name = "id")String id,
                               Model model){

        QuestionDTO questionDTO=questionService.getById(id);
        model.addAttribute("questionUser",questionDTO);
        return "questionPage";
    }
}
