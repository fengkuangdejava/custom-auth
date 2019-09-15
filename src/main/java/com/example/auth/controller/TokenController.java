package com.example.auth.controller;


import com.example.auth.model.AccessToken;
import com.example.auth.service.imp.DefaultTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    @Autowired
    DefaultTokenService defaultTokenService;

    public AccessToken refreshToken(String refreshToken){

        return defaultTokenService.refreshToken(refreshToken);
    }

    public boolean refreshClient(){
        return false;
    }
}
