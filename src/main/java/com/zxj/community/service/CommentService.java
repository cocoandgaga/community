package com.zxj.community.service;

import com.zxj.community.exception.CommentTypeEnum;
import com.zxj.community.exception.CustomizeErrorCode;
import com.zxj.community.exception.CustomizeException;
import com.zxj.community.mapper.CommentMapper;
import com.zxj.community.mapper.QuestionExtMapper;
import com.zxj.community.mapper.QuestionMapper;
import com.zxj.community.model.Comment;
import com.zxj.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper    questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Transactional
    public  void insert(Comment comment){
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
            commentMapper.insertSelective(comment);
        }else{
            //评论
            Question question=questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question==null)
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUNT);
            commentMapper.insertSelective(comment);
            question.setCommentCnt(1);
            questionExtMapper.incCommentCnt(question);
        }
    }
}
