package com.dhd.controller;

import com.dhd.domain.User;
import com.dhd.interceptor.UserInfoGetter;
import com.dhd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserInfoGetter userInfoGetter;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/toLogin")
    public String toLogin(User user, Model model,HttpSession httpSession) {//进行登录验证
        if (userService.checkUser(user)) {//验证账号密码是否已在用户数据库内
            user = userService.getUser(user.getAccount(), user.getPassword());
            httpSession.setAttribute("onlineUser", user);
            userInfoGetter.setUser(user);
            return "index"; //进入对应的主界面
        }
        model.addAttribute("wrong", "用户名或密码错误，请重新登录！");//设置警告信息
        return "login";//返回登录界面
    }

    @PostMapping("/checkUser")
    //检验数据格式
    public String checkUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {//注册数据校验
        if (bindingResult.hasErrors()) {//如果数据校验有误，则返回注册界面
            return "register";
        }
        //如果数据校验无误，则将数据添加到数据库中
        if (userService.createUser(user)) {
            return "login";
        } else {
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {//退出登录
        httpSession.invalidate();//清除
        userInfoGetter.UserLogout();//当前用户退出
        return "login";
    }
}
