package com.test.project.controller;

import com.alibaba.fastjson.JSON;
import com.test.project.entity.Plate;
import com.test.project.entity.PlateMessage;
import com.test.project.entity.User;
import com.test.project.service.PlateMessageService;
import com.test.project.service.PlateService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-14
 * Time: 上午9:16
 * Description:
 *
 * @author chen
 */
@Controller
@RequestMapping(value = "/plateMessage")
public class PlateMessageController {

    private PlateMessageService plateMessageService;

    private PlateService plateService;

    /**
     * 获取板块所有的消息
     *
     * @param model   数据模型
     * @param plateId 板块id
     * @return 列表
     */
    @GetMapping(value = "/list/{id}")
    public String getAllPLateMessage(Model model, @PathVariable("id") int plateId) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        Plate plate = plateService.getPlate(plateId);
        model.addAttribute("plate", plate);
        List<PlateMessage> plateMessageList = plateMessageService.getByPlateId(plateId);
        model.addAttribute("plateMessageList", plateMessageList);
        return "plateMessageList";
    }

    /**
     * 获取板块所有的消息
     *
     * @param plateId 板块id
     * @return 列表
     */
    @GetMapping(value = "/list/get/{id}")
    @ResponseBody
    public String getAllPLateMessageByPost(@PathVariable("id") int plateId) {
        List<PlateMessage> plateMessageList = plateMessageService.getByPlateId(plateId);
        return JSON.toJSONString(plateMessageList);
    }

    /**
     * 根据消息id查看详情
     *
     * @param id 消息id
     * @return 查看详情
     */
    @GetMapping(value = "/detail/{id}")
    public String getPlateMessageDetail(Model model, @PathVariable("id") int id) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        // 增加访问量
        plateMessageService.addInstructions(id);
        PlateMessage plateMessage = plateMessageService.getById(id);
        model.addAttribute("plateMessage", plateMessage);
        return "plateMessageDetail";
    }

    @Autowired
    public void setPlateMessageService(PlateMessageService plateMessageService) {
        this.plateMessageService = plateMessageService;
    }

    @Autowired
    public void setPlateService(PlateService plateService) {
        this.plateService = plateService;
    }
}
