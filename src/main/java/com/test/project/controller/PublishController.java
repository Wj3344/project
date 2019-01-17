package com.test.project.controller;

import com.test.project.entity.Plate;
import com.test.project.entity.Uploaded;
import com.test.project.entity.User;
import com.test.project.model.MessageLink;
import com.test.project.service.PlateService;
import com.test.project.service.UploadService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by handsome programmer.
 *
 * Date: 19-1-13
 * Time: 下午8:48
 * Description:  信息发布管理
 *
 *
 */
@Controller
@RequestMapping(value = "/publish")
public class PublishController {

    private PlateService plateService;

    private UploadService uploadService;

    /**
     * 请求到信息发布界面
     *
     * @param model 模型
     * @return 请求页面
     */
    @GetMapping(value = "")
    public String publishPage(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        // 获取所有的板块
        List<Plate> plateList = plateService.getAll();
        model.addAttribute("plateList", plateList);
        return "publish";
    }

    @PostMapping(value = "/add")
    public String addPublish(Uploaded uploadedMessage, Model model) {
        System.out.println(uploadedMessage.toString());
        int add = uploadService.add(uploadedMessage);
        MessageLink messageLink = new MessageLink();
        if (add == 0) {
            // 发布失败
            messageLink.setMessage("发布失败！");
        } else {
            //发布成功
            messageLink.setMessage("发布成功！");
        }
        messageLink.setLink("/index");
        model.addAttribute("messageLink", messageLink);
        return "showMessage";
    }

    @GetMapping(value = "/audit")
    public String auditPage(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        // 获取所有可以操作的列表
        List<Uploaded> uploadList = uploadService.getUploadList(user.getId());
        model.addAttribute("uploadList", uploadList);
        //跳转到消息审核页面
        return "auditPage";
    }

    /**
     * 审核通过
     *
     * @return 跳转到审核页面
     */
    @RequestMapping(value = "/audit/success/{id}")
    public String auditSuccess(@PathVariable("id") int uploadId) {
        System.out.println("审核通过的id： " + uploadId);
        uploadService.modifyStatus(uploadId,true);
        return "redirect:/publish/audit";
    }

    /**
     * 审核失败
     *
     * @return 跳转到审核页面
     */
    @RequestMapping(value = "/audit/fail/{id}")
    public String auditFail(@PathVariable("id") int uploadId) {
        System.out.println("审核不通过的id： " + uploadId);
        // 修改数据库的标记即可
        uploadService.modifyStatus(uploadId,false);
        return "redirect:/publish/audit";
    }

    @Autowired
    public void setPlateService(PlateService plateService) {
        this.plateService = plateService;
    }

    @Autowired
    public void setUploadService(UploadService uploadService) {
        this.uploadService = uploadService;
    }
}