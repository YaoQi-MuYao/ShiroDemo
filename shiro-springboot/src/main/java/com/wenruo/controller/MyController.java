package com.wenruo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName MyController
 * @Description TODO
 * @Author MuYao
 * @Date 2020/4/28 9:56
 * @Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/my")
public class MyController {

    @RequestMapping("/index")
    public String toIndex(Model model) {
        model.addAttribute("msg", "Hello Shiro");
        return "index";
    }

    @RequestMapping("/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/update")
    public String update() {
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model) {

        /* 获取当前的用户 */
        Subject subject = SecurityUtils.getSubject();

        /* 封装用户的登陆数据 */
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            /* 执行登录的方法，如果没有异常就说明ok了 */
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) { /* 用户名错误异常 */
            model.addAttribute("msg", "用户名错误");
            log.error("登录错误 ===========> 用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) { /* 密码错误异常 */
            model.addAttribute("msg", "密码错误");
            log.error("密码错误 ===========> 密码错误");
            return "login";
        }
    }
}
