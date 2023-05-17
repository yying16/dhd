package com.dhd.controller;

import com.dhd.domain.User;
import com.dhd.interceptor.UserInfoGetter;
import com.dhd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//针对用户信息的控制器(用户查看自己信息、修改自己密码等)

@RestController
@RequestMapping("/infos")
public class UserInfoController {
    @Autowired
    private UserInfoGetter userInfoGetter;
    @Autowired
    private UserService userService;

    //当前用户获取自己的信息
    @GetMapping("/getMyInfo")
    public User getMyInfo() {
        return userInfoGetter.getUser();
    }

}
