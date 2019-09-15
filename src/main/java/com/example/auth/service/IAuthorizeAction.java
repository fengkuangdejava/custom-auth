package com.example.auth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权登录类
 * */
public interface IAuthorizeAction {
    void authorize(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
