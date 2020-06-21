package com.zxj.community.mapper;

import com.zxj.community.model.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionExtMapper {
    int incViewCnt(Question record);

    int incCommentCnt(Question record);
}
