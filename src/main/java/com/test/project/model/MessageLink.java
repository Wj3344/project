package com.test.project.model;

import java.io.Serializable;

/**
 * Created by handsome programmer.
 *
 * Date: 19-1-13
 * Time: 下午2:44
 * Description:
 *
 *
 */
public class MessageLink implements Serializable {

    private static final long serialVersionUID = -6609770120505488241L;

    /**
     * 提示消息
     */
    private String message;
    /**
     * 跳转连接
     */
    private String link;

    public MessageLink() {
        this.link = "/index";
        this.message = "成功";
    }

    @Override
    public String toString() {
        return "MessageLink{" +
                "message='" + message + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
