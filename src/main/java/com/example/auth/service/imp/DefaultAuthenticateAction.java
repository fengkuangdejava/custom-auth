package com.example.auth.service.imp;

import com.example.auth.service.IAuthenticateAction;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 默认的鉴权实现
 * */
public class DefaultAuthenticateAction implements IAuthenticateAction {
    @Override
    public void authenticate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain){
        //从参数中获取token 去redis取 没取到 报异常 取到了 接着走
        //取到后根据clientID 查一遍数据库
        //匹一下过期时间 和权限范围  不匹配，报错 客户端凭证过期或者 客户端权限不够
        //然后根据用户角色 匹一下 requestUrl;查看是否匹配  不匹配 报用户没有权限 匹配 这dofilter;
    }
}
