package com.test.project.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分消费记录
 */

public class IntegralConsumption implements Serializable {

    private static final long serialVersionUID = -8780271887568844014L;
    /**
     * 记录id
     */
    private Integer id;

    /**
     * 消费者
     */
    private Integer consumer;

    /**
     * 受益者
     */
    private Integer beneficiary;

    /**
     * 消费金额
     */
    private Integer value;

    /**
     * 消费时间
     */
    private Date time;

    @Override
    public String toString() {
        return "IntegralConsumption{" +
                "id=" + id +
                ", consumer=" + consumer +
                ", beneficiary=" + beneficiary +
                ", value=" + value +
                ", time=" + time +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConsumer() {
        return consumer;
    }

    public void setConsumer(Integer consumer) {
        this.consumer = consumer;
    }

    public Integer getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Integer beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}