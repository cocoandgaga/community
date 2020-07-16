package com.zxj.community.service;

import com.zxj.community.dto.CommentDTO;
import com.zxj.community.enumType.CommentTypeEnum;
import com.zxj.community.enumType.NotificationStatusEnum;
import com.zxj.community.enumType.NotificationTypeEnum;
import com.zxj.community.exception.CustomizeErrorCode;
import com.zxj.community.exception.CustomizeException;
import com.zxj.community.mapper.*;
import com.zxj.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper    questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    CommentExtMapper commentExtMapper;
    @Autowired
    NotificationMapper notificationMapper;

    @Transactional
    public  void insert(Comment comment,User commentator){
        if(comment.getParentId()==null||comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.PARENT_QUESTION_NOT_FOUND);
        }
        if(comment.getType()==null||!CommentTypeEnum.isExist(comment.getType()) ){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment=commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment==null)
                throw  new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);

            Question question=questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if(question==null)
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUNT);

            commentMapper.insertSelective(comment);
            dbComment.setCommentCnt(1);
            commentExtMapper.incCommentCnt(dbComment);
            Question questionComment=questionMapper.selectByPrimaryKey(dbComment.getParentId());
            question.setCommentCnt(1);
            questionExtMapper.incCommentCnt(question);
            createNotify(comment, dbComment.getCommentator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_COMMENT, question.getId());

        }else{
            //评论
            Question question=questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question==null)
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUNT);
            commentMapper.insertSelective(comment);
            question.setCommentCnt(1);
            questionExtMapper.incCommentCnt(question);
            createNotify(comment, question.getCreator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_QUESTION, question.getId());
        }
    }

    private void createNotify(Comment comment, Long receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType, Long outerId) {
        if (receiver == comment.getCommentator()) {
            return;
        }
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setOuterId(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> listByQuestionId(Long id, CommentTypeEnum type) {
        //拿到所有一级评论
        CommentExample example=new CommentExample();
        example.createCriteria().
                andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());//选中alt+p 抽出类型
        example.setOrderByClause("gmt_create desc");
        List<Comment> comments=commentMapper.selectByExample(example);

        if(comments.size()==0) {
            return  Collections.emptyList();
        }

        //得到用户id
        Set<Long> commentators=comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds=new ArrayList<>();
        userIds.addAll(commentators);

        //根据用户id得到所有评论的用户
        UserExample userExample=new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<User> users= userMapper.selectByExample(userExample);
        Map<Long,User> userMap=users.stream().collect(Collectors.toMap(user-> user.getId(), user->user));
        List<CommentDTO> commentDTOs=comments.stream().map(comment->{
            CommentDTO commentDTO=new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOs;
    }
}
