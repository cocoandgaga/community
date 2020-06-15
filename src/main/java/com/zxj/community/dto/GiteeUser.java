package com.zxj.community.dto;

import lombok.Data;

/**
 * 得到的用户消息
 */
@Data
public class GiteeUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
