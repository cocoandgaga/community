package com.zxj.community.controller;

import com.zxj.community.dto.PaginationDTO;
import com.zxj.community.model.User;
import com.zxj.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/my/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          HttpServletRequest request,
                          Model model){

        User user= (User) request.getSession().getAttribute("user");

        if(user==null) return "redirect:/";

        if("myQuestions".equals(action)){
            model.addAttribute("section","myQuestions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","回复");
        }
        PaginationDTO paginationDTO=questionService.list(user.getId(),page,size);
        model.addAttribute("pagination",paginationDTO);
        return "my";

    }

}
