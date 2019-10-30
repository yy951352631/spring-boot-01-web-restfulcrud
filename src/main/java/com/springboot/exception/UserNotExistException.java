package com.springboot.exception;

/**
 * @author Wtq
 * @date 2019/8/13 - 10:25
 */
public class UserNotExistException  extends RuntimeException{
    public UserNotExistException() {
        super("用户不存在");
    }
}
