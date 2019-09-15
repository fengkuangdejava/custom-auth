package com.example.auth.handler;

import com.example.auth.model.ClientDetail;
import com.example.auth.model.UserAuth;
import com.example.auth.model.WapperRequest;

import java.io.IOException;

public interface IAuthorizeSucessHandler {
    void dealSucess(ClientDetail cilentDetail, UserAuth user, WapperRequest wapperRequest) throws IOException;
}
