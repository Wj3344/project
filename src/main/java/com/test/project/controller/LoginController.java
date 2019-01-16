package com.test.project.controller;

import com.test.project.entity.*;
import com.test.project.mapper.UserMapper;
import com.test.project.model.MessageLink;
import com.test.project.service.PlateAdminService;
import com.test.project.service.PlateMessageService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午7:15
 * Description:
 *
 * @author chen
 */
@Controller
public class LoginController {

    /**
     * 用户数据dao
     */
    private UserMapper userMapper;

    private PlateMessageService plateMessageService;

    private PlateAdminService plateAdminService;

    /**
     * 返回登录页面
     *
     * @return 登录页面
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        try {
            subject.login(token);
            assert requestAttributes != null;
            requestAttributes.setAttribute("responseMessage", ResponseMessage.ok(), RequestAttributes.SCOPE_SESSION);
            requestAttributes.setAttribute("fanchen", "我的名字是哈哈", RequestAttributes.SCOPE_SESSION);
            return "index";
        } catch (UnknownAccountException | LockedAccountException | IncorrectCredentialsException e) {
            assert requestAttributes != null;
            requestAttributes.setAttribute("responseMessage", ResponseMessage.error(e.getMessage()), RequestAttributes.SCOPE_SESSION);
        } catch (AuthenticationException e) {
            assert requestAttributes != null;
            requestAttributes.setAttribute("responseMessage", ResponseMessage.error("认证失败！"), RequestAttributes.SCOPE_SESSION);
        }
        //这是转发
        return "forward:/errorPage";
    }

    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        // 获取六个板块的6条消息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("username", user.getUsername());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        requestAttributes.setAttribute("user", user, RequestAttributes.SCOPE_SESSION);
        model.addAttribute("identity", user.getIdentity());
        // 查询是否是板块管理员或者板块助理
        List<PlateAdmin> byAdminId = plateAdminService.findByAdminId(user.getId());
        if (byAdminId.size() == 0) {
            model.addAttribute("isAdmin", false);
        } else {
            model.addAttribute("isAdmin", true);
        }

        // 从数据库中拉取最新的36条记录
        List<PlateMessage> plateMessageList = plateMessageService.getPlateMessageListByNumber(36);
        model.addAttribute("plateMessageList", plateMessageList);
        return "index";
    }

    @RequestMapping(value = "/errorPage")
    public String errorPage(Model model) {
        System.out.println("进入error控制器");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        ErrorMessage errorMessage = new ErrorMessage();
        ResponseMessage responseMessage = (ResponseMessage) requestAttributes.getAttribute("responseMessage", RequestAttributes.SCOPE_SESSION);
        errorMessage.setTitle("登录失败");
        errorMessage.setHead((String) responseMessage.get("msg"));
        errorMessage.setMessage("<a href=\"/index\">返回</a>");
        model.addAttribute("errorMessage", errorMessage);
        return "errorPage";
    }

    /**
     * 处理registration get请求
     *
     * @return registration页面
     */
    @RequestMapping(value = "/registration")
    public String registration() {
        return "registration";
    }

    /**
     * 处理注册用户请求
     *
     * @return 处理结果返回页面
     */
    @PostMapping(value = "/registration")
    public String doRegistration(User user, Model model) {
        System.out.println(user);
        MessageLink messageLink = new MessageLink();
        int registration = 0;
        try {
            registration = userMapper.registration(user);
        } catch (Exception e) {
            System.out.println("注册信息已存在");
        }
        if (registration == 0) {
            messageLink.setMessage("注册失败！重新注册,注册信息已存在");
            messageLink.setLink("/registration");
        } else {
            messageLink.setMessage("注册成功！返回登录");
            messageLink.setLink("/login");
        }
        model.addAttribute("messageLink", messageLink);
        return "showMessage";
    }


    /**
     * 处理registration get请求
     *
     * @return registration页面
     */
    @RequestMapping(value = "/getPassword")
    public String getPassword() {
        return "getPassword";
    }

    /**
     * 处理找回密码的请求
     *
     * @return 处理结果返回页面
     */
    @PostMapping(value = "/getPassword")
    public String doGetPassword(String username, String email, int studentId, Model model) {
        System.out.println(email + " --- " + studentId);
        MessageLink messageLink = new MessageLink();
        String password = userMapper.getPassword(username, email, studentId);
        if (password == null) {
            messageLink.setMessage("查询条件错误！重新找回");
            messageLink.setLink("/getPassword");
        } else {
            messageLink.setMessage("找回成功！你的密码为：" + password + " 返回登录！");
            messageLink.setLink("/login");
        }
        model.addAttribute("messageLink", messageLink);

        return "showMessage";
    }


    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setPlateMessageService(PlateMessageService plateMessageService) {
        this.plateMessageService = plateMessageService;
    }

    @Autowired
    public void setPlateAdminService(PlateAdminService plateAdminService) {
        this.plateAdminService = plateAdminService;
    }
}
