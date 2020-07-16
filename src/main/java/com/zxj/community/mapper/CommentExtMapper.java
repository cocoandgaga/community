package com.zxj.community.mapper;

import com.zxj.community.model.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentExtMapper {
    int incCommentCnt(Comment comment);
}
