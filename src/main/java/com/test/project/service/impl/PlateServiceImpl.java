package com.test.project.service.impl;

import com.test.project.entity.Plate;
import com.test.project.entity.User;
import com.test.project.exception.MyAddException;
import com.test.project.mapper.PlateMapper;
import com.test.project.model.Guestbook;
import com.test.project.service.PlateService;
import com.test.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-13
 * Time: 下午9:16
 * Description:
 *
 * @author chen
 */
@Service
public class PlateServiceImpl implements PlateService {
    private PlateMapper plateMapper;

    private UserService userService;

    @Override
    public int addPlate(Plate plate) {
        return plateMapper.insert(plate);
    }

    @Override
    public int deletePlate(int id) {
        return 0;
    }

    @Override
    @Transactional(rollbackFor = MyAddException.class)
    public int updatePlate(Plate plate) {
        int i = plateMapper.updateByPrimaryKey(plate);
        if (i == 0) {
            throw new MyAddException("修改板块信息失败：" + plate.toString());
        }
        // 修改user身份信息
        User user = new User();
        user.setId(plate.getAdmin());
        user.setIdentity(2);
        i = userService.modifyUserIdentity(user);
        if (i == 0) {
            throw new MyAddException("修改板块负责人身份信息失败：");
        }
        return i;
    }

    @Override
    public Plate getPlate(int id) {
        return plateMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Plate> getAll() {
        return plateMapper.getAllPlate();
    }

    @Override
    public List<Guestbook> getMessage(int id, int number) {
        return null;
    }

    @Override
    public List<Guestbook> getAllMessage(int id) {
        return null;
    }

    @Override
    public int deleteMessage(int messageId) {
        return 0;
    }

    @Override
    public List<Plate> getPlateByAdminId(Integer id) {
        return plateMapper.findByAdmin(id);
    }

    @Autowired
    public void setPlateMapper(PlateMapper plateMapper) {
        this.plateMapper = plateMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
