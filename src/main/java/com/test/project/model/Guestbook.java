package com.test.project.model;

import java.util.Date;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-12
 * Time: 下午2:39
 * Description: 板块留言表
 *
 * @author chen
 */
public class Guestbook {
    /**
     * 留言编号
     */
    private int id;
    /**
     * 留言人的昵称
     */
    private String nickName;
    /**
     * 留言作者id
     */
    private int author;
    /**
     * 留言消息内容
     */
    private String message;
    /**
     * 留言时间
     */
    private Date time;

    @Override
    public String toString() {
        return "Guestbook{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", author=" + author +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
