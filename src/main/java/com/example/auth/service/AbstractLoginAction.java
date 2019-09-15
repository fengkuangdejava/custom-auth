package com.example.auth.service;

import com.example.auth.exception.DefaultException;
import com.example.auth.model.WapperRequest;
import com.example.auth.model.UserAuth;
import lombok.Data;

import java.util.List;

@Data
public abstract class AbstractLoginAction {
    private List<String> loginUrls;

    public AbstractLoginAction(List<String> loginUrls) {
        this.loginUrls = loginUrls;
    }

    public AbstractLoginAction(String loginUrl) {
        this.loginUrls.add(loginUrl);
    }

    public abstract UserAuth doLogin(WapperRequest wapperRequest) throws DefaultException;
}
