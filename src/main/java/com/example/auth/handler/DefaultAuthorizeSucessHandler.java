package com.example.auth.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.auth.model.*;
import com.example.auth.service.TokenService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultAuthorizeSucessHandler implements IAuthorizeSucessHandler{
    TokenService tokenService;

    @Override
    public void dealSucess(ClientDetail cilentDetail, UserAuth user,WapperRequest wapperRequest) throws IOException {
        HttpServletResponse response = wapperRequest.getHttpServletResponse();
        AuthResult authResult = new DefaultAuthResult(cilentDetail,user);
        AccessToken accessToken = tokenService.createAccesToken(authResult);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(ResultFactory.success(accessToken)));
    }
}
