package com.test.project.service.impl;

import com.test.project.entity.Plate;
import com.test.project.mapper.PlateMapper;
import com.test.project.model.Guestbook;
import com.test.project.service.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public int addPlate(Plate plate) {
        return 0;
    }

    @Override
    public int deletePlate(int id) {
        return 0;
    }

    @Override
    public int updatePlate(Plate plate) {
        return 0;
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

    @Autowired
    public void setPlateMapper(PlateMapper plateMapper) {
        this.plateMapper = plateMapper;
    }
}
