package com.zxj.community.controller;

import com.zxj.community.dto.CommentDTO;
import com.zxj.community.dto.QuestionDTO;
import com.zxj.community.enumType.CommentTypeEnum;
import com.zxj.community.model.User;
import com.zxj.community.service.CommentService;
import com.zxj.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuestionPageController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/questionPage/{id}")
    public String questionPage(@PathVariable(name = "id") Long id,
                               Model model,
                               HttpServletRequest request){

        User user= (User) request.getSession().getAttribute("user");
        if(user!=null)
            model.addAttribute("user",user);
        QuestionDTO questionDTO=questionService.getById(id);
        List<QuestionDTO> relatedQuestions=questionService.selectRelated(questionDTO);
        //得到该评论下的所有一级回复 含用户和内容
        List<CommentDTO> commentDTOS =commentService.listByQuestionId(id, CommentTypeEnum.QUESTION);
        //每次一点开问题 问题就增加阅读量
        questionService.incViewCnt(id);
        model.addAttribute("questionUser",questionDTO);
        model.addAttribute("comments",commentDTOS);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "questionPage";
    }
}
