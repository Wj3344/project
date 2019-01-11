package com.test.project.entity;

import java.io.Serializable;

public class Identity implements Serializable {


    private static final long serialVersionUID = -6222473820006805283L;
    /**
     * 记录id
     */
    private Integer id;

    /**
     * 记录描述
     */
    private String description;

    @Override
    public String toString() {
        return "Identity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}