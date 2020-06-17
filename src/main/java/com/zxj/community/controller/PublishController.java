package com.zxj.community.controller;

import com.zxj.community.dto.QuestionDTO;
import com.zxj.community.mapper.QuestionMapper;
import com.zxj.community.model.Question;
import com.zxj.community.model.User;
import com.zxj.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public  String doEdit(@PathVariable(name = "id")Integer id,
                          Model model){
        QuestionDTO questionDTO=questionService.getById(id);
        model.addAttribute("title",questionDTO.getTitle());
        model.addAttribute("description",questionDTO.getDescription());
        model.addAttribute("tag",questionDTO.getTag());
        model.addAttribute("id",questionDTO.getId());
        return  "publish";
    }

    @GetMapping("/publish")
    public String doPublish(){return "publish";}

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false)String title,
                            @RequestParam(value = "description",required = false)String description,
                            @RequestParam(value = "tag",required = false)String tag,
                            @RequestParam(value = "id",required = false)Integer id,
                            HttpServletRequest request, Model model){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title==null|| title.equals("")){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null|| description.equals("")){
            model.addAttribute("error","问题描述不能为空");
            return "publish";
        }
        if(tag==null|| tag.equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user= (User) request.getSession().getAttribute("user");

        if(user==null) {
            model.addAttribute("error","用户未登陆");
            return "publish";
        }
        Question question=new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setId(id);
        question.setCreator(user.getAccountId());
        questionService.createOrUpdate(question);
        return "redirect:/";
    }


}
