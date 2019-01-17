package com.test.project.controller;

import com.test.project.entity.Plate;
import com.test.project.entity.PlateMessage;
import com.test.project.entity.User;
import com.test.project.model.MessageLink;
import com.test.project.model.PlateList;
import com.test.project.service.PlateMessageService;
import com.test.project.service.PlateService;
import com.test.project.service.UserService;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by handsome programmer.
 *
 * Date: 19-1-14
 * Time: 下午3:36
 * Description:
 *
 *
 */
@Controller
@RequestMapping(value = "/plate")
public class PlateController {

    private PlateService plateService;

    private UserService userService;

    private PlateMessageService plateMessageService;

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

    /**
     * 处理modify的post请求
     *
     * @param plate 请求参数
     * @return 处理结果
     */
    @PostMapping(value = "/manager/modify")
    public String plateManagerModify(Plate plate, Model model) {
        System.out.println(plate);
        int i = plateService.updatePlate(plate);
        MessageLink messageLink = new MessageLink();
        if (i == 0) {
            messageLink.setMessage("修改失败!");
        } else {
            messageLink.setMessage("修改成功!");
        }
        messageLink.setLink("/plate/manager/modify/" + plate.getId());
        model.addAttribute("messageLink", messageLink);
        return "showMessage";
    }

    /**
     * 添加一个板块
     *
     * @param model 模型
     * @return 跳转到添加页面
     */
    @GetMapping(value = "/manager/add")
    public String addPlate(Model model) {
        // 获取所有用户
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        return "addPlatePage";
    }

    /**
     * 添加一个板块
     *
     * @param plate 板块信息
     * @param model 模型
     * @return 跳转到添加结果页面
     */
    @PostMapping(value = "/manager/add")
    public String addPlate(Model model, Plate plate) {
        int i = plateService.addPlate(plate);
        MessageLink messageLink = new MessageLink();
        if (i == 0) {
            messageLink.setMessage("添加失败!");
        } else {
            messageLink.setMessage("添加成功!");
        }
        messageLink.setLink("/plate/manager/add");
        model.addAttribute("messageLink", messageLink);
        return "showMessage";
    }

    /**
     * 删除一个板块
     *
     * @return 删除结果
     */
    @GetMapping(value = "/manager/delete/{plateId}")
    public String deletePlate(@PathVariable("plateId") int id, Model model) {
        if(id <= 6)
        {
            return "redirect:/plate/manager";
        }else{
            plateService.deletePlate(id);
            return "redirect:/plate/manager";
        }
    }

    /**
     * 列出所有的板块信息
     *
     * @param model 模型
     * @return 列表页面
     */
    @GetMapping(value = "/list")
    public String listPlatePage(Model model) {
        List<PlateList> plateLists = new LinkedList<>();
        // 查询所有的板块列表
        List<Plate> plateList = plateService.getAll();
        for (Plate plate : plateList) {
            List<PlateMessage> messages = plateMessageService.getByIdAndNumber(plate.getId(), 6);
            PlateList p = new PlateList();
            p.setPlate(plate);
            p.setPlateMessageList(messages);
            plateLists.add(p);
        }
        model.addAttribute("plateLists", plateLists);
        return "listPlates";
    }

    @Autowired
    public void setPlateService(PlateService plateService) {
        this.plateService = plateService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPlateMessageService(PlateMessageService plateMessageService) {
        this.plateMessageService = plateMessageService;
    }
}
