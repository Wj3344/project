package com.test.project.service.impl;

import com.test.project.entity.PlateAdmin;
import com.test.project.entity.PlateMessage;
import com.test.project.entity.Uploaded;
import com.test.project.entity.User;
import com.test.project.exception.MyAddException;
import com.test.project.mapper.PlateAdminMapper;
import com.test.project.mapper.PlateMessageMapper;
import com.test.project.mapper.UploadedMapper;
import com.test.project.mapper.UserMapper;
import com.test.project.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by handsome programmer.
 *
 * Date: 19-1-13
 * Time: 下午9:27
 * Description:
 *
 *
 */
@Service
public class UploadServiceImpl implements UploadService {
    private UploadedMapper uploadedMapper;

    private UserMapper userMapper;

    private PlateMessageMapper plateMessageMapper;

    private PlateAdminMapper plateAdminMapper;

    @Override
    public int add(Uploaded uploaded) {
        return uploadedMapper.insert(uploaded);
    }

    @Override
    public List<Uploaded> getUploadList(Integer id) {
        // 根据管理员id查询所有的管理板块
        User user = userMapper.selectByPrimaryKey(id);
        List<PlateAdmin> plateAdmins = plateAdminMapper.findByAdminId(id);
        List<Uploaded> uploadedList = new LinkedList<>();
        for (PlateAdmin plateAdmin : plateAdmins) {
            List<Uploaded> allByPlateIdOrOrderByTime = uploadedMapper.findAllByPlateIdOrOrderByTime(plateAdmin.getPlateId());
            uploadedList.addAll(allByPlateIdOrOrderByTime);
        }
        return uploadedList;
    }

    @Override
    public int modifyStatus(int uploadId, boolean b) {
        if (b) {
            return modifyStatusSuccess(uploadId);
        } else {
            return modifyStatusFail(uploadId);
        }
    }

    @Override
    @Transactional(rollbackFor = MyAddException.class)
    public int modifyStatusSuccess(int uploadId) {
        // 修改数据库状态
        Uploaded uploaded = new Uploaded();
        uploaded.setId(uploadId);
        uploaded.setStatus("通过");
        int i = uploadedMapper.updateMessageStatus(uploaded);
        if (i == 0) {
            // 修改状态失败，抛出异常
            throw new MyAddException("修改状态失败！");
        }
        // 查询修改记录
        uploaded = uploadedMapper.selectByPrimaryKey(uploadId);
        if (uploaded == null) {
            throw new MyAddException("查询消息失败！");
        }
        // 将文章写入到对应板块
        PlateMessage plateMessage = new PlateMessage();
        plateMessage.setTitle(uploaded.getTitle());
        plateMessage.setContent(uploaded.getContent());
        plateMessage.setPlateId(uploaded.getPlateId());
        plateMessage.setUserId(uploaded.getUserId());
        plateMessage.setValue(uploaded.getValue());
        plateMessage.setBlock(false);
        i = plateMessageMapper.insert(plateMessage);
        if (i == 0) {
            throw new MyAddException("数据添加到板块消息失败！");
        }
        return i;
    }

    @Override
    public int modifyStatusFail(int uploadId) {
        // 修改数据库状态
        Uploaded uploaded = new Uploaded();
        uploaded.setId(uploadId);
        uploaded.setStatus("未通过");
        return uploadedMapper.updateMessageStatus(uploaded);
    }

    @Autowired
    public void setUploadedMapper(UploadedMapper uploadedMapper) {
        this.uploadedMapper = uploadedMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setPlateAdminMapper(PlateAdminMapper plateAdminMapper) {
        this.plateAdminMapper = plateAdminMapper;
    }

    @Autowired
    public void setPlateMessageMapper(PlateMessageMapper plateMessageMapper) {
        this.plateMessageMapper = plateMessageMapper;
    }
}
