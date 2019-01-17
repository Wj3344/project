package com.test.project.controller;

import com.test.project.entity.Plate;
import com.test.project.entity.User;
import com.test.project.model.MessageLink;
import com.test.project.model.PlateAssistant;
import com.test.project.service.PlateAdminService;
import com.test.project.service.PlateService;
import com.test.project.service.UserService;
import org.apache.shiro.SecurityUtils;
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
 * Date: 19-1-15
 * Time: 下午12:19
 * Description:
 *
 *
 */
@Controller
@RequestMapping("/boardManager")
public class BoardManagerController {

    /**
     * 板块服务
     */
    private PlateService plateService;
    /**
     * 板块助理服务
     */
    private PlateAdminService plateAdminService;

    private UserService userService;

    /**
     * 请求到板块管理页面(板块负责人身份)
     *
     * @param model 模型
     * @return 跳转页面
     */
    @GetMapping(value = "/manager")
    public String manager(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        // 查询所负责的板块信息
        List<Plate> plateList = plateService.getPlateByAdminId(user.getId());
        List<PlateAssistant> assistantList = new LinkedList<>();
        for (Plate p : plateList) {
            PlateAssistant plateAssistant = new PlateAssistant();
            plateAssistant.setPlate(p);
            // 查询板块助理列表
            List<User> users = plateAdminService.findAssistantByPlateId(p.getId());
            plateAssistant.setUserList(users);
            assistantList.add(plateAssistant);
        }
        model.addAttribute("assistantList", assistantList);
        return "boardManager";
    }

    /**
     * 添加一个板块助理
     *
     * @param plateId 板块id
     * @param model   模型
     * @return 添加页面
     */
    @GetMapping(value = "/assistant/add/{plateId}")
    public String addAssistant(@PathVariable("plateId") int plateId, Model model) {
        Plate plate = plateService.getPlate(plateId);
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        model.addAttribute("plate", plate);
        return "addAssistant";
    }

    @PostMapping(value = "/assistant/add")
    public String addAssistantPost(int plateId, int userId,Model model) {
        int i = plateAdminService.addAssistant(plateId, userId);
        MessageLink messageLink = new MessageLink();
        messageLink.setLink("/boardManager/manager");
        if (i == 0) {
            // 添加失败
            messageLink.setMessage("添加板块助理失败!");
        } else {
            // 添加成功
            messageLink.setMessage("添加板块助成功!");
        }
        model.addAttribute("messageLink",messageLink);
        return "showMessage";
    }

    @GetMapping(value = "/assistant/delete/{plateId}+{id}")
    public String deleteAssistant(@PathVariable("plateId") int plateId,@PathVariable("id")int userId){
        plateAdminService.delete(plateId,userId);
        return "redirect:/boardManager/manager";
    }

    @Autowired
    public void setPlateService(PlateService plateService) {
        this.plateService = plateService;
    }

    @Autowired
    public void setPlateAdminService(PlateAdminService plateAdminService) {
        this.plateAdminService = plateAdminService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
