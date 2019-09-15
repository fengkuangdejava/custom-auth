package com.example.auth.service;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 鉴权类
 * */
public interface IAuthenticateAction {
    void  authenticate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain);
}
