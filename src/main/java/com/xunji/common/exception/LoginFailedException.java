package com.xunji.common.exception;

/**
 * 登录失败异常
 */
public class LoginFailedException extends BaseException{
    public LoginFailedException(String msg){
        super(msg);
    }
}
