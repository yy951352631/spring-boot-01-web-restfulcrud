package com.springboot.component;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wtq
 * @date 2019/8/8 - 14:05
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    /*
    实现登录检检查
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //方法执行前
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            //未登录
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        } else {
            //已登录
            return true;
        }
    }

}
