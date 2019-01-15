package com.test.project.service;

import com.test.project.entity.PlateAdmin;
import com.test.project.entity.User;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-14
 * Time: 上午9:06
 * Description:
 *
 * @author chen
 */
public interface PlateAdminService {
    /**
     * 根据管理员id查询负责板块
     *
     * @param adminId 管理员id
     * @return 查询结果
     */
    List<PlateAdmin> findByAdminId(int adminId);

    /**
     * 根据板块id 查询板块助理
     *
     * @param plateId 板块id
     * @return 查询列表
     */
    List<PlateAdmin> findByPlateId(int plateId);

    /**
     * 根据板块id查询板块助理信息
     *
     * @param id 板块ID
     * @return 查询结果
     */
    List<User> findAssistantByPlateId(Integer id);

    /**
     * 添加一个板块助理
     * @param plateId 板块id
     * @param userId 助理id
     * @return 添加结果
     */
    int addAssistant(int plateId, int userId);

    /**
     * 删除一条记录
     * @param plateId 板块id
     * @param userId 用户id
     * @return 删除结果
     */
    int delete(int plateId, int userId);
}
