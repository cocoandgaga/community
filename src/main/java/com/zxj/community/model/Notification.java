package com.zxj.community.model;

import org.springframework.stereotype.Repository;

@Repository
public class Notification {
    private Long id;
    private Long notifier;
    private Long receiver; //
    private Long outerId;
    private Integer type;
    private Long gmtCreate;
    private Integer status;
    private String notifierName;
    private String outerTitle;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getNotifier() {
        return notifier;
    }
    public void setNotifier(Long notifier) {
        this.notifier = notifier;
    }
    public Long getReceiver() {
        return receiver;
    }
    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }
    public Long getOuterId() {
        return outerId;
    }
    public void setOuterId(Long outerId) {
        this.outerId = outerId;
    }
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.TYPE
     *
     * @param type the value for NOTIFICATION.TYPE
     *
     * @mbg.generated Wed Jul 15 22:11:25 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.GMT_CREATE
     *
     * @return the value of NOTIFICATION.GMT_CREATE
     *
     * @mbg.generated Wed Jul 15 22:11:25 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.GMT_CREATE
     *
     * @param gmtCreate the value for NOTIFICATION.GMT_CREATE
     *
     * @mbg.generated Wed Jul 15 22:11:25 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.STATUS
     *
     * @return the value of NOTIFICATION.STATUS
     *
     * @mbg.generated Wed Jul 15 22:11:25 CST 2020
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.STATUS
     *
     * @param status the value for NOTIFICATION.STATUS
     *
     * @mbg.generated Wed Jul 15 22:11:25 CST 2020
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.NOTIFIER_NAME
     *
     * @return the value of NOTIFICATION.NOTIFIER_NAME
     *
     * @mbg.generated Wed Jul 15 22:11:25 CST 2020
     */
    public String getNotifierName() {
        return notifierName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.NOTIFIER_NAME
     *
     * @param notifierName the value for NOTIFICATION.NOTIFIER_NAME
     *
     * @mbg.generated Wed Jul 15 22:11:25 CST 2020
     */
    public void setNotifierName(String notifierName) {
        this.notifierName = notifierName == null ? null : notifierName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.OUTER_TITLE
     *
     * @return the value of NOTIFICATION.OUTER_TITLE
     *
     * @mbg.generated Wed Jul 15 22:11:25 CST 2020
     */
    public String getOuterTitle() {
        return outerTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.OUTER_TITLE
     *
     * @param outerTitle the value for NOTIFICATION.OUTER_TITLE
     *
     * @mbg.generated Wed Jul 15 22:11:25 CST 2020
     */
    public void setOuterTitle(String outerTitle) {
        this.outerTitle = outerTitle == null ? null : outerTitle.trim();
    }
}