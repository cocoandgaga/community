package com.zxj.community.controller;

import com.zxj.community.dto.PaginationDTO;
import com.zxj.community.dto.QuestionDTO;
import com.zxj.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size){

        //获取问题列表(包含用户头像
        PaginationDTO<QuestionDTO> pagination=questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
