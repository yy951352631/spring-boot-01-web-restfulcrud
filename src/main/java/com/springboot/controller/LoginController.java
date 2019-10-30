package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Wtq
 * @date 2019/8/8 - 11:05
 */
@Controller
//@RequestMapping(value = "/user")
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, Map<String, Object> map
            , HttpSession session
    ) {
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //登录成功
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        } else {
            //登录失败
            map.put("msg", "用户名密码错误");
            return "login";
        }
    }
}
