package com.example.auth.service.imp;

import com.example.auth.exception.DefaultException;
import com.example.auth.model.BCryptPasswordEncoder;
import com.example.auth.model.PasswordEncoder;
import com.example.auth.model.UserAuth;
import com.example.auth.model.WapperRequest;
import com.example.auth.service.AbstractLoginAction;
import com.example.auth.service.UserService;

import java.util.List;

public class MobileLoginAction extends AbstractLoginAction {
    private UserService userServiceImpl;
    private PasswordEncoder pe;

    public MobileLoginAction(List<String> loginUrls, UserService userServiceImpl, PasswordEncoder pe) {
        super(loginUrls);
        this.userServiceImpl = userServiceImpl;
        this.pe = pe;
    }

    public MobileLoginAction(String loginUrl, UserService userServiceImpl, PasswordEncoder pe) {
        super(loginUrl);
        this.userServiceImpl = userServiceImpl;
        this.pe = pe;
    }

    @Override
    public UserAuth doLogin(WapperRequest wapperRequest) throws DefaultException {
        String mobile = wapperRequest.getHttpServletRequest().getParameter("mobile");
        if(mobile==null) {
            throw new DefaultException("请输入手机号");
        }
        UserAuth userAuth = userServiceImpl.findByMobile(mobile);
        if(userAuth==null){
            throw new DefaultException("手机号未找到");
        }else {
            return userAuth;
        }
    }
}
