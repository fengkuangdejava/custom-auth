package com.example.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http请求包装类
 *
 * @author liting email:fengkuangdejava@outlook.com
 * */
@Data
@AllArgsConstructor
public class WapperRequest {
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private AccessToken accessToken;
    private boolean authorized;

    public WapperRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
    }
}
