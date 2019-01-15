package com.test.project.model;

import com.test.project.entity.Plate;
import com.test.project.entity.PlateMessage;

import java.io.Serializable;
import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-15
 * Time: 上午1:21
 * Description:
 *
 * @author chen
 */
public class PlateList implements Serializable {

    private static final long serialVersionUID = -3070386798856733638L;

    /**
     * 板块信息
     */
    private Plate plate;

    /**
     * 板块消息列表
     */
    private List<PlateMessage> plateMessageList;

    @Override
    public String toString() {
        return "PlateList{" +
                "plate=" + plate +
                ", plateMessageList=" + plateMessageList +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public List<PlateMessage> getPlateMessageList() {
        return plateMessageList;
    }

    public void setPlateMessageList(List<PlateMessage> plateMessageList) {
        this.plateMessageList = plateMessageList;
    }
}
