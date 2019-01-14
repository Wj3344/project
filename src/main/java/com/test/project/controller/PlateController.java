package com.test.project.controller;

import com.test.project.entity.Plate;
import com.test.project.entity.User;
import com.test.project.service.PlateService;
import com.test.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-14
 * Time: 下午3:36
 * Description:
 *
 * @author chen
 */
@Controller
@RequestMapping(value = "/plate")
public class PlateController {

    private PlateService plateService;

    private UserService userService;

    /**
     * 请求板块管理页面
     *
     * @return 页面跳转
     */
    @GetMapping(value = "/manager")
    public String plateManagerPage(Model model) {
        List<Plate> plateList = plateService.getAll();
        model.addAttribute("plateList", plateList);
        return "plateManager";
    }


    /**
     * 请求跳转到板块修改页面
     *
     * @return 跳转
     */
    @GetMapping(value = "/manager/modify/{plateId}")
    public String plateManagerModify(@PathVariable("plateId") int id, Model model) {
        Plate plate = plateService.getPlate(id);
        model.addAttribute("plate", plate);
        // 获取所有用户
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        return "plateModify";
    }

    @Autowired
    public void setPlateService(PlateService plateService) {
        this.plateService = plateService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
