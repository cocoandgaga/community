package com.zxj.community.dto;

import lombok.Data;

@Data
public class NotificationDTO {
        private Long id;
        private Long notifier;
        private Long outerId;
        private Integer type;
        private Long gmtCreate;
        private Integer status;
        private String notifierName;
        private String outerTitle;
        private String typeName;//

}
