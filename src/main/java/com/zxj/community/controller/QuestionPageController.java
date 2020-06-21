package com.zxj.community.controller;

import com.zxj.community.dto.QuestionDTO;
import com.zxj.community.model.User;
import com.zxj.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionPageController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questionPage/{id}")
    public String questionPage(@PathVariable(name = "id") Long id,
                               Model model, HttpServletRequest request){

        User user= (User) request.getSession().getAttribute("user");
        if(user!=null)
            model.addAttribute("user",user);
        QuestionDTO questionDTO=questionService.getById(id);
        questionService.incViewCnt(id);
        model.addAttribute("questionUser",questionDTO);
        return "questionPage";
    }
}
