package com.test.project.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户已经查看的板块消息的记录
 */
public class Downloaded implements Serializable {

    private static final long serialVersionUID = -2953824108269738583L;
    /**
     * 记录id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 板块消息id
     */
    private Integer messageId;

    /**
     * 查看时间
     */
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}