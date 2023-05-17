package com.dhd.interceptor;

import com.dhd.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    //重写preHandle方法（在控制器方法执行之前检验是否已填写账号信息）
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("onlineUser");//获得已填写的账号信息
        if (user == null) {//如果没有填写账号密码信息
            request.setAttribute("wrong", "请先进行登录");//设置警告信息
            request.getRequestDispatcher("/login").forward(request, response);//转发到登录界面
            return false;
        }
        request.setAttribute("wrong", "");//清除警告信息
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

    }
}
