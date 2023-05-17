package com.dhd.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhd.domain.User;
import com.dhd.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    //判断该用户账号密码是否正确
    public boolean checkUser(User user) {
        try {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("account", user.getAccount());
            wrapper.eq("password", user.getPassword());
            return userMapper.selectOne(wrapper) != null;
        } catch (Exception e) {
            return false;
        }
    }

    //根据账号和密码放回对应的User
    public User getUser(String account, String password) {
        try {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("account", account);
            wrapper.eq("password", password);
            return userMapper.selectOne(wrapper);
        } catch (Exception e) {
            return null;
        }
    }

    //判断用户是否已经注册
    public boolean checkUserHasRegister(String account) {
        try {
            return userMapper.selectById(account) != null;
        } catch (Exception e) {
            return false;
        }
    }

    //注册用户，将用户的数据写入数据库(创建成功则返回true，失败则返回false)
    public boolean createUser(User user) {
        try {
            userMapper.insert(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getUsername(String account){
        try{
            return userMapper.selectById(account).getUsername();
        }catch (Exception e){
            return "";
        }
    }

}
