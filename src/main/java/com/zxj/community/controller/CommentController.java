package com.zxj.community.controller;

import com.zxj.community.dto.CommentCreateDTO;
import com.zxj.community.dto.CommentDTO;
import com.zxj.community.dto.ResultDTO;
import com.zxj.community.enumType.CommentTypeEnum;
import com.zxj.community.exception.CustomizeErrorCode;
import com.zxj.community.model.Comment;
import com.zxj.community.model.Notification;
import com.zxj.community.model.User;
import com.zxj.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){

        User user=(User)request.getSession().getAttribute("user");
        if(user ==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        //commons-lang3包的函数
        if(commentCreateDTO==null|| StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment=new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        commentService.insert(comment, user);
        return ResultDTO.okOf();
    }
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id")Long id){
        List<CommentDTO> commentDTOS= commentService.listByQuestionId(id,CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
