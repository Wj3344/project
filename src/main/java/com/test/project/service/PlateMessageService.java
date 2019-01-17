package com.test.project.service;

import com.test.project.entity.PlateMessage;
import com.test.project.entity.Uploaded;

import java.util.List;

/**
 * Created by handsome programmer.
 *
 * Date: 19-1-12
 * Time: 下午3:26
 * Description:
 *
 *
 */
public interface PlateMessageService {
    /**
     * 根据id获取板块消息的内容
     *
     * @param id id
     * @return 查询结果
     */
    PlateMessage getById(int id);

    /**
     * 获取一个板块下的一部分内容
     *
     * @param plateId 板块id
     * @param number  数量
     * @return 查询结果
     */
    List<PlateMessage> getByIdAndNumber(int plateId, int number);

    /**
     * 获取一个板块下的所有消息
     *
     * @param plateId 板块id
     * @return 查询结果
     */
    List<PlateMessage> getByPlateId(int plateId);


    /********** 板块消息审核 start ************/
    /**
     * 获取板块下所有未审核的消息
     *
     * @param plateId 板块id
     * @return 查询结果
     */
    List<Uploaded> getUploadByPlateId(int plateId);

    /**
     * 修改用户上传的消息状态
     *
     * @param id     消息id
     * @param status 状态编号：0 未处理 1 审核通过   2 审核不通过
     * @return 修改结果
     */
    int modifyUploadStatus(int id, int status);

    /********** 板块消息审核 end ************/

    /**
     * 增加访问量
     *
     * @param id 消息id
     * @return 增加结果
     */
    int addInstructions(int id);

    /**
     * 拉取数据库中的最新的记录
     *
     * @param number 数量
     * @return 查询列表
     */
    List<PlateMessage> getPlateMessageListByNumber(int number);
}
