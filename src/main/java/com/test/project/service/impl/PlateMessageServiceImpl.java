package com.test.project.service.impl;

import com.test.project.entity.PlateMessage;
import com.test.project.entity.Uploaded;
import com.test.project.mapper.PlateMapper;
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

    @Override
    public PlateMessage getById(int id) {
        return null;
    }

    @Override
    public List<PlateMessage> getByIdAndNumber(int plateId, int number) {
        return plateMapper.getByIdAndNumber(plateId, number);
    }

    @Override
    public List<PlateMessage> getByPlateId(int plateId) {
        return null;
    }

    @Override
    public List<Uploaded> getUploadByPlateId(int plateId) {
        return null;
    }

    @Override
    public int modifyUploadStatus(int id, int status) {
        return 0;
    }

    @Autowired
    public void setPlateMapper(PlateMapper plateMapper) {
        this.plateMapper = plateMapper;
    }
}
