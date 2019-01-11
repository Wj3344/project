package com.test.project.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 私信表
 */

public class PrivateLetter implements Serializable {

    private static final long serialVersionUID = 7669989846146695453L;
    /**
     * 记录id
     */
    private Integer id;
    /**
     * 发件人id
     */
    private Integer author;

    /**
     * 收件人id
     */
    private Integer receiver;

    /**
     * 发件信息
     */
    private String message;

    /**
     * 发件时间
     */
    private Date time;

    /**
     * 是否查看，0未查看，1已查看
     */
    private Boolean sign;

    @Override
    public String toString() {
        return "PrivateLetter{" +
                "id=" + id +
                ", author=" + author +
                ", receiver=" + receiver +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", sign=" + sign +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getSign() {
        return sign;
    }

    public void setSign(Boolean sign) {
        this.sign = sign;
    }
}