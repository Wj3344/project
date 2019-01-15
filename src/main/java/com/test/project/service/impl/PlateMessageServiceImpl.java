package com.test.project.service.impl;

import com.test.project.entity.PlateMessage;
import com.test.project.entity.Uploaded;
import com.test.project.mapper.PlateMapper;
import com.test.project.mapper.PlateMessageMapper;
import com.test.project.service.PlateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-13
 * Time: 下午5:03
 * Description:
 *
 * @author chen
 */
@Service
public class PlateMessageServiceImpl implements PlateMessageService {
    /**
     * dao
     */
    private PlateMapper plateMapper;

    private PlateMessageMapper plateMessageMapper;

    @Override
    public PlateMessage getById(int id) {
        return plateMessageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PlateMessage> getByIdAndNumber(int plateId, int number) {
        return plateMapper.getByIdAndNumber(plateId, number);
    }

    @Override
    public List<PlateMessage> getByPlateId(int plateId) {
        return plateMessageMapper.findAllByPlateId(plateId);
    }

    @Override
    public List<Uploaded> getUploadByPlateId(int plateId) {
        return null;
    }

    @Override
    public int modifyUploadStatus(int id, int status) {
        return 0;
    }

    @Override
    public int addInstructions(int id) {
        return plateMessageMapper.addInstructions(id);
    }

    @Override
    public List<PlateMessage> getPlateMessageListByNumber(int number) {
        return plateMessageMapper.getPlateMessageListByNumber(number);
    }

    @Autowired
    public void setPlateMapper(PlateMapper plateMapper) {
        this.plateMapper = plateMapper;
    }

    @Autowired
    public void setPlateMessageMapper(PlateMessageMapper plateMessageMapper) {
        this.plateMessageMapper = plateMessageMapper;
    }
}
