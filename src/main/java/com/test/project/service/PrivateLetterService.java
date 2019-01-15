package com.test.project.service;

import com.test.project.entity.PrivateLetter;
import com.test.project.model.PrivateLetterList;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-13
 * Time: 下午7:18
 * Description:
 *
 * @author chen
 */
public interface PrivateLetterService {

    /**
     * 查询所有私信列表
     *
     * @param receiver 收件人id
     * @return 私信列表
     */
    List<PrivateLetterList> getByReceiverId(int receiver);

    /**
     * 将私信标记为已读
     *
     * @param id 私信id
     * @return 标记结果
     */
    int mark(int id);

    /**
     * 删除
     *
     * @param id id
     * @return 删除结果
     */
    int delete(int id);

    /**
     * 发送私信
     *
     * @param privateLetter 私信内容
     * @return 发送结果
     */
    int insert(PrivateLetter privateLetter);
}
