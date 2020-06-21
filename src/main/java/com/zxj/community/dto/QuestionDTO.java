package com.zxj.community.dto;

import com.zxj.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    private Long creator;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer viewCnt;
    private Integer commentCnt;
    private Integer likeCnt;
    private User    user;
}
