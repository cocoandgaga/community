package com.zxj.community.dto;

import com.zxj.community.model.User;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class CommentDTO {

    private Long id;
    private Long parentId;
    private Integer type;
    private String content;
    private Long commentator;
    private Long gmtCreate;
    private Long likeCnt;
    private Integer commentCnt;
    private User user;
}
