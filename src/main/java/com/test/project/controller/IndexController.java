package com.test.project.controller;

import com.test.project.entity.User;
import com.test.project.model.PrivateLetterList;
import com.test.project.service.PrivateLetterService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping("/privateLetterList")
    public String privateLetterList(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("username", user.getUsername());
        // 查询用户的私信列表
        List<PrivateLetterList> letterLists = privateLetterService.getByReceiverId(user.getId());
        model.addAttribute("letterLists", letterLists);
        return "privateLetterList";
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
}
