package com.example.auth.service.imp;

import com.example.auth.exception.DefaultException;
import com.example.auth.model.PasswordEncoder;
import com.example.auth.model.UserAuth;
import com.example.auth.model.WapperRequest;
import com.example.auth.service.AbstractLoginAction;
import com.example.auth.service.UserService;

import java.util.List;

public class UserNameAndPasswordLoginAction extends AbstractLoginAction {

    private UserService userServiceImpl;
    private PasswordEncoder pe;

    public UserNameAndPasswordLoginAction(List<String> loginUrls, UserService userServiceImpl, PasswordEncoder pe) {
        super(loginUrls);
        this.userServiceImpl = userServiceImpl;
        this.pe = pe;
    }

    public UserNameAndPasswordLoginAction(String loginUrl, UserService userServiceImpl, PasswordEncoder pe) {
        super(loginUrl);
        this.userServiceImpl = userServiceImpl;
        this.pe = pe;
    }

    @Override
    public UserAuth doLogin(WapperRequest wapperRequest) throws DefaultException {
        String username = wapperRequest.getHttpServletRequest().getParameter("username");
        String password = wapperRequest.getHttpServletRequest().getParameter("password");
        if(username==null) {
            throw new DefaultException("请输入用户名");
        }
        if(password==null) {
            throw new DefaultException("请输入密码");
        }
         UserAuth userAuth = userServiceImpl.findUserByName(password);
         if(userAuth==null){
            throw new DefaultException("用户名未找到");
         }else if(!pe.matches(password,userAuth.getPassword())){
             throw new DefaultException("密码错误");
         }else{
             return userAuth;
         }
    }
}
