package com.test.project.controller;

import com.test.project.entity.ErrorMessage;
import com.test.project.entity.ResponseMessage;
import com.test.project.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        String fanchen = (String) requestAttributes.getAttribute("fanchen", RequestAttributes.SCOPE_SESSION);
        System.out.println(fanchen);
        return "index";
    }

    @RequestMapping(value = "/errorPage")
    public String errorPage(Model model) {
        System.out.println("进入error控制器");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        ErrorMessage errorMessage = (ErrorMessage) requestAttributes.getAttribute("errorMessage", RequestAttributes.SCOPE_SESSION);
        if (errorMessage == null) {
            errorMessage = new ErrorMessage();
            errorMessage = new ErrorMessage();
            errorMessage.setTitle("暂无权限");
            errorMessage.setHead("您没有权限进行该操作");
            errorMessage.setMessage("<a href=\"/index\">返回</a>");
        }
        model.addAttribute("errorMessage", errorMessage);
        return "errorPage";
    }

}
