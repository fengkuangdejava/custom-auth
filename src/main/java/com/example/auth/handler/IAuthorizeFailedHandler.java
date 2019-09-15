package com.example.auth.handler;

import com.example.auth.model.WapperRequest;

//登录失败处理
public interface IAuthorizeFailedHandler {
    void dealFailed(WapperRequest wapperRequest,Exception e);
}
