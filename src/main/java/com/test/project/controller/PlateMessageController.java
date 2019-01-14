package com.test.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    /**
     * 获取板块所有的消息
     *
     * @param model   数据模型
     * @param plateId 板块id
     * @return 列表
     */
    @GetMapping(value = "/list/{id}")
    public String getAllPLateMessage(Model model, @PathVariable("id") int plateId) {
        return null;
    }
}
