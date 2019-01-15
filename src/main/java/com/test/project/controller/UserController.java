package com.test.project.controller;

import com.test.project.entity.User;
import com.test.project.model.MessageLink;
import com.test.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-15
 * Time: 上午1:47
 * Description:
 *
 * @author chen
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    /**
     * 跳转到修改用户基本信息界面
     *
     * @return 页面跳转
     */
    @GetMapping(value = "/modifyUserMessage")
    public String modifyUserMessage() {
        return "modifyUserMessage";
    }

    /**
     * 用户基本信息
     *
     * @return 修改结果
     */
    @PostMapping(value = "/modifyUserMessage")
    public String modifyUserMessage(Model model, User user) {
        System.out.println(user.toString());
        int i = userService.modifyUserMessage(user);
        MessageLink messageLink = new MessageLink();
        if (i == 0) {
            messageLink.setMessage("修改失败");
        } else {
            messageLink.setMessage("修改成功");
        }
        messageLink.setLink("/user/modifyMessage");
        model.addAttribute("messageLink", messageLink);
        return "showMessage";
    }


    /**
     * 跳转到修改用户密码信息界面
     *
     * @return 页面跳转
     */
    @GetMapping(value = "/modifyUserPassword")
    public String modifyUserPassword() {
        return "modifyUserPassword";
    }

    /**
     * 修改用户密码信息
     *
     * @return 页面跳转
     */
    @PostMapping(value = "/modifyUserPassword")
    public String modifyUserPassword(Model model, int id, String oldPassword, String newPassword) {
        User user = userService.getUserById(id);
        MessageLink messageLink = new MessageLink();
        messageLink.setLink("/user/modifyUserPassword");
        if (oldPassword == null || !oldPassword.equals(user.getPassword())) {
            messageLink.setMessage("旧密码错误.");
            model.addAttribute("messageLink", messageLink);
            return "showMessage";
        }
        int i = userService.modifyUserPassword(id, newPassword);
        if (i == 0) {
            messageLink.setMessage("修改用户密码失败");
        } else {
            messageLink.setMessage("修改用户密码成功");
        }
        model.addAttribute("messageLink", messageLink);
        return "showMessage";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
