package com.test.project.service.impl;

import com.test.project.entity.Plate;
import com.test.project.entity.PlateMessage;
import com.test.project.entity.User;
import com.test.project.exception.MyAddException;
import com.test.project.mapper.*;
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

    private UploadedMapper uploadedMapper;

    private PlateMessageMapper plateMessageMapper;

    private PlateAdminMapper plateAdminMapper;
    private MessageMapper mesageMapper;

    @Override
    public int addPlate(Plate plate) {
        return plateMapper.insert(plate);
    }

    @Override
    @Transactional(rollbackFor = MyAddException.class)
    public int deletePlate(int id) {
        // 删除板块上传消息
        uploadedMapper.deleteByPlateId(id);
        // 删除板块消息
        plateMessageMapper.deleteByPlateId(id);
        // 删除板块负责人
        plateAdminMapper.deleteByPLateId(id);
        // 删除留言表
        mesageMapper.deleteByPlateId(id);
        // 删除板块
        plateMapper.deleteByPrimaryKey(id);
        return 1;
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

    @Autowired
    public void setUploadedMapper(UploadedMapper uploadedMapper) {
        this.uploadedMapper = uploadedMapper;
    }

    @Autowired
    public void setPlateMessageMapper(PlateMessageMapper plateMessageMapper) {
        this.plateMessageMapper = plateMessageMapper;
    }

    @Autowired
    public void setPlateAdminMapper(PlateAdminMapper plateAdminMapper) {
        this.plateAdminMapper = plateAdminMapper;
    }

    @Autowired
    public void setMesageMapper(MessageMapper mesageMapper) {
        this.mesageMapper = mesageMapper;
    }
}
