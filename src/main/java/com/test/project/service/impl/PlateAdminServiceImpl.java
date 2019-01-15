package com.test.project.service.impl;

import com.test.project.entity.PlateAdmin;
import com.test.project.entity.User;
import com.test.project.exception.MyAddException;
import com.test.project.mapper.PlateAdminMapper;
import com.test.project.mapper.UserMapper;
import com.test.project.service.PlateAdminService;
import com.test.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
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

    private UserMapper userMapper;

    private UserService userService;

    @Override
    public List<PlateAdmin> findByAdminId(int adminId) {
        return plateAdminMapper.findByAdminId(adminId);
    }

    @Override
    public List<PlateAdmin> findByPlateId(int plateId) {
        return plateAdminMapper.findByPlateId(plateId);
    }

    @Override
    public List<User> findAssistantByPlateId(Integer id) {
        List<User> userList = new LinkedList<>();
        List<PlateAdmin> plateAdminList = findByPlateId(id);
        for (PlateAdmin plateAdmin : plateAdminList) {
            User userById = userService.getUserById(plateAdmin.getUserId());
            userList.add(userById);
        }
        return userList;
    }

    @Override
    @Transactional(rollbackFor = MyAddException.class)
    public int addAssistant(int plateId, int userId) {
        // 往助理表中添加一条记录
        PlateAdmin plateAdmin = new PlateAdmin();
        plateAdmin.setPlateId(plateId);
        plateAdmin.setUserId(userId);
        int insert = plateAdminMapper.insert(plateAdmin);
        if(insert == 0){
            // 插入记录失败
            throw new MyAddException("插入板块助理记录失败! 板块id:" + plateId+ " , 助理id:" +userId);
        }
        // 修改用户身份等级
        User u = new User();
        u.setIdentity(3);
        u.setId(userId);
        insert = userMapper.updateUserIdentity(u);
        if(insert == 0){
            // 修改身份失败
            throw new MyAddException("修改身份失败! 板块id:" + plateId+ " , 助理id:" +userId);
        }
        return insert;
    }

    @Override
    public int delete(int plateId, int userId) {
        return plateAdminMapper.deleteByUserIdAndPlateId(plateId,userId);
    }

    @Autowired
    public void setPlateAdminMapper(PlateAdminMapper plateAdminMapper) {
        this.plateAdminMapper = plateAdminMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
