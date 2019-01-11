package com.test.project.entity;

import java.io.Serializable;

public class Plate implements Serializable {

    private static final long serialVersionUID = 4880228327628595724L;

    /**
     * 记录id
     */
    private Integer id;

    /**
     * 板块名称
     */
    private String name;

    /**
     * 板块介绍
     */
    private String referral;

    /**
     * 般快速负责人id
     */
    private Integer admin;

    @Override
    public String toString() {
        return "Plate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", referral='" + referral + '\'' +
                ", admin=" + admin +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral == null ? null : referral.trim();
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
}