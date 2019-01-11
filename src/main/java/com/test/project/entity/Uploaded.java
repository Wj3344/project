package com.test.project.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户上传的板块消息的列表
 */
public class Uploaded implements Serializable {

    private static final long serialVersionUID = -4655279265561278627L;
    /**
     * 记录id
     */
    private Integer id;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 上传时间
     */
    private Date time;

    /**
     * 板块id
     */
    private Integer plateId;

    /**
     * 上传者的id
     */
    private Integer userId;

    /**
     * 积分价值
     */
    private Integer value;

    /**
     * 状态
     */
    private String status;

    /**
     * 板块消息内容
     */
    private String content;

    public Uploaded() {
        this.time = new Date();
        this.status = "未处理";
    }

    @Override
    public String toString() {
        return "Uploaded{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", time=" + time +
                ", plateId=" + plateId +
                ", userId=" + userId +
                ", value=" + value +
                ", status='" + status + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getPlateId() {
        return plateId;
    }

    public void setPlateId(Integer plateId) {
        this.plateId = plateId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}