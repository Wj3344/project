package com.test.project.service;

import com.test.project.entity.PlateAdmin;

/**
 * Created by handsome programmer.
 *
 * Date: 19-1-12
 * Time: 下午3:07
 * Description: 板块负责人服务层
 *
 *
 */
public interface BoardLeaderService {


    /**
     * 添加一个板块助理
     *
     * @param plateAdmin 板块助理信息
     * @return 添加结果
     */
    int addAssistant(PlateAdmin plateAdmin);

    /**
     * 删除一个板块助理
     *
     * @param id id
     * @return 删除结果
     */
    int deleteAssistant(int id);
}
