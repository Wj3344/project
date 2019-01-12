package com.test.project.service;

import com.test.project.entity.Plate;
import com.test.project.model.Guestbook;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-12
 * Time: 下午2:06
 * Description: 板块管理服务
 *
 * @author chen
 */
public interface PlateService {
    /**
     * 添加一个板块
     *
     * @param plate 板块消息
     * @return 添加结果
     */
    int addPlate(Plate plate);

    /**
     * 删除一个板块
     *
     * @param id 板块id
     * @return 删除结果
     */
    int deletePlate(int id);

    /**
     * 修改板块信息
     *
     * @param plate 板块信息
     * @return 修改结果
     */
    int updatePlate(Plate plate);

    /**
     * 插叙编号为id的板块信息
     *
     * @param id id
     * @return 查询结果
     */
    Plate getPlate(int id);

    /**
     * 获取所有的板块信息
     *
     * @return
     */
    List<Plate> getAll();

    /**
     * 获取id板块的前number条留言
     *
     * @param id     板块id
     * @param number 数量
     * @return 查询结果
     */
    List<Guestbook> getMessage(int id, int number);

    /**
     * 获取板块的所有留言
     *
     * @param id 板块id
     * @return 留言列表
     */
    List<Guestbook> getAllMessage(int id);

    /**
     * 删除留言信息
     *
     * @param messageId 信息id
     * @return 删除结果
     */
    int deleteMessage(int messageId);
}
