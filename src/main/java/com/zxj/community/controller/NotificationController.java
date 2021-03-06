package com.zxj.community.controller;

import com.zxj.community.dto.NotificationDTO;
import com.zxj.community.enumType.NotificationTypeEnum;
import com.zxj.community.model.User;
import com.zxj.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("notification/{id}")
    public String my(HttpServletRequest request, @PathVariable(name = "id")Long id){
        User user=(User) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
        NotificationDTO notificationDTO=notificationService.read(id,user);
        if(NotificationTypeEnum.REPLY_COMMENT.getType()==notificationDTO.getType()||NotificationTypeEnum.REPLY_QUESTION.getType()==notificationDTO.getType())
            return "redirect:/questionPage/"+notificationDTO.getOuterId();
        else
           return "redirect:/";

    }
}
