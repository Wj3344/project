package com.test.project.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 留言表
 */

public class Message implements Serializable {

    private static final long serialVersionUID = -7355193971587498229L;

    /**
     * 记录id
     */
    private Integer id;

    /**
     * 留言人id
     */
    private Integer author;

    /**
     * 收信人id
     */
    private Integer receiver;

    /**
     * 留言信息
     */
    private String message;

    /**
     * 留言信息内容
     */
    private Date time;

    /**
     * 留言板块id
     */
    private Integer plateMessageId;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", receiver=" + receiver +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", plateMessageId=" + plateMessageId +
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

    public Integer getPlateMessageId() {
        return plateMessageId;
    }

    public void setPlateMessageId(Integer plateMessageId) {
        this.plateMessageId = plateMessageId;
    }
}