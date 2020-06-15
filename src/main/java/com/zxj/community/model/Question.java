package com.zxj.community.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private Integer creator;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer viewCnt;
    private Integer commentCnt;
    private Integer likeCnt;

}
