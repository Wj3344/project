package com.test.project.model;

import com.test.project.entity.Plate;
import com.test.project.entity.User;

import java.util.List;

/**
 * Created by handsome programmer.
 *
 * Date: 19-1-15
 * Time: 下午4:46
 * Description: 板块助理
 *
 *
 */
public class PlateAssistant {
    /**
     * 板块
     */
    private Plate plate;

    /**
     * 板块助理
     */
    private List<User> userList;

    @Override
    public String toString() {
        return "PlateAssistant{" +
                "plate=" + plate +
                ", userList=" + userList +
                '}';
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
