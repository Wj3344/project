package com.test.project.service;

import com.test.project.entity.PlateAdmin;

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
}
