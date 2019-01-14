package com.test.project.service.impl;

import com.test.project.entity.PlateAdmin;
import com.test.project.mapper.PlateAdminMapper;
import com.test.project.service.PlateAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-14
 * Time: 上午9:08
 * Description:
 *
 * @author chen
 */
@Service
public class PlateAdminServiceImpl implements PlateAdminService {

    private PlateAdminMapper plateAdminMapper;

    @Override
    public List<PlateAdmin> findByAdminId(int adminId) {
        return plateAdminMapper.findByAdminId(adminId);
    }

    @Autowired
    public void setPlateAdminMapper(PlateAdminMapper plateAdminMapper) {
        this.plateAdminMapper = plateAdminMapper;
    }
}
