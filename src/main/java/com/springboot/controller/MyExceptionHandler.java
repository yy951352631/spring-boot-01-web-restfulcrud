package com.springboot.controller;

import com.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wtq
 * @date 2019/8/13 - 10:38
 */
@ControllerAdvice
public class MyExceptionHandler {
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String, Object> handleException(Exception e) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "user.notExist");
//        map.put("message", e.getMessage());
//        return map;
//    }
            @ExceptionHandler(UserNotExistException.class)
            public String handleException(Exception e ,HttpServletRequest request) {
                Map<String, Object> map = new HashMap<>();
                request.setAttribute("javax.servlet.error.status_code",500);
                map.put("code", "user.notExist");
                map.put("message", e.getMessage());
                request.setAttribute("ext",map);
                return "forward:/error";
    }
}
