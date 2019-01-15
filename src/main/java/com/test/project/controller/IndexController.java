package com.test.project.controller;

import com.test.project.entity.Message;
import com.test.project.entity.PrivateLetter;
import com.test.project.entity.User;
import com.test.project.mapper.UserMapper;
import com.test.project.model.MessageLink;
import com.test.project.model.PrivateLetterList;
import com.test.project.service.PrivateLetterService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-13
 * Time: 上午2:07
 * Description:
 *
 * @author chen
 */
@Controller
public class IndexController {

    private PrivateLetterService privateLetterService;

    private UserMapper userMapper;

    @RequestMapping("/privateLetterList")
    public String privateLetterList(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("username", user.getUsername());
        // 查询用户的私信列表
        List<PrivateLetterList> letterLists = privateLetterService.getByReceiverId(user.getId());
        model.addAttribute("letterLists", letterLists);
        return "privateLetterList";
    }

    @GetMapping(value = "/sendPrivateLetter")
    public String sendPrivateLetter(Model model) {
        List<User> userList = userMapper.getAllUser();
        model.addAttribute("userList", userList);
        return "sendPrivateLetter";
    }

    @PostMapping(value = "/sendPrivateLetter")
    public String sendPrivateLetterSave(PrivateLetter privateLetter, Model model) {
        int insert = privateLetterService.insert(privateLetter);
        MessageLink messageLink = new MessageLink();
        if (insert == 0) {
            // 发送失败
            messageLink.setMessage("发送失败!");
        } else {
            // 发送成功
            messageLink.setMessage("发送成功!");
        }
        messageLink.setLink("/index");
        model.addAttribute("messageLink", messageLink);
        return "showMessage";
    }

    /**
     * 将私信标记为已读
     *
     * @param model 模型
     * @param id    私信id
     */
    @RequestMapping(value = "/privateLetterList/mark/{id}", method = RequestMethod.GET)
    public String privateLetterList(Model model, @PathVariable("id") int id) {
        int mark = privateLetterService.mark(id);
        if (mark == 0) {
            System.out.println("标记失败");
        } else {
            System.out.println("标记成功");
        }
        return "redirect:/privateLetterList";
    }

    /**
     * 将私信标记为已读
     *
     * @param model 模型
     * @param id    私信id
     */
    @RequestMapping(value = "/privateLetterList/delete/{id}", method = RequestMethod.GET)
    public String deletePrivateLetter(Model model, @PathVariable("id") int id) {
        int delete = privateLetterService.delete(id);
        if (delete == 0) {
            System.out.println("删除失败");
        } else {
            System.out.println("删除成功");
        }
        return "redirect:/privateLetterList";
    }


    @Autowired
    public void setPrivateLetterService(PrivateLetterService privateLetterService) {
        this.privateLetterService = privateLetterService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
