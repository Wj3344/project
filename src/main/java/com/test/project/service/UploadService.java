package com.test.project.service;

import com.test.project.entity.Uploaded;

import java.util.List;

/**
 * Created by handsome programmer.
 *
 * Date: 19-1-13
 * Time: 下午9:26
 * Description:
 *
 *
 */
public interface UploadService {
    /**
     * 添加一个上传的消息记录
     *
     * @param uploaded 上传的消息记录
     * @return 添加结果
     */
    int add(Uploaded uploaded);

    /**
     * 获取管理板块下所有的审核消息
     *
     * @param id 管理员id
     * @return 列表
     */
    List<Uploaded> getUploadList(Integer id);

    /**
     * 修改上传记录的状态
     *
     * @param uploadId 记录id
     * @param b
     * @return 修改结果
     */
    int modifyStatus(int uploadId, boolean b);

    /**
     * 审核通过
     *
     * @param uploadId 记录id
     * @return 修改结果
     */
    int modifyStatusSuccess(int uploadId);

    /**
     * 审核失败
     *
     * @param uploadId 记录id
     * @return 修改结果
     */
    int modifyStatusFail(int uploadId);

}
