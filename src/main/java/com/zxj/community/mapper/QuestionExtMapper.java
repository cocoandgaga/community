package com.zxj.community.mapper;

import com.zxj.community.model.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionExtMapper {
    int incViewCnt(Question record);
     List<Question> selectRelated(Question question);
    int incCommentCnt(Question record);
}
