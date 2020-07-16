package com.zxj.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUNT(2001 ,"你要找的问题可能已删除或不存在..."),
    UPDATE_FAILED(2002,"更新失败，您要更新的问题可能已经删除"),
    PARENT_QUESTION_NOT_FOUND(2003,"当前问题或评论已被删除"),
    NO_LOGIN(2004,"当前操作需要登陆，请登陆!"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2005,"当前评论已被删除"),
    TYPE_NOT_FOUND(2006,""),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空"),
    SYS_ERROR(5005,"服务器发生错误"),
    NOTIFICATION_NOT_FOUND(2008,"通知未找到"),
    READ_NOTIFICATION_FAIL(2009,"读取通知失败");
    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code,String message) {
        this.code=code;
        this.message=message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
