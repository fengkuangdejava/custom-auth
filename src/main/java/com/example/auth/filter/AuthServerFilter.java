package com.example.auth.filter;
import com.example.auth.service.IAuthorizeAction;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Data
public class AuthServerFilter extends OncePerRequestFilter implements InitializingBean {
    private IAuthorizeAction authorizeAction;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    public AuthServerFilter(IAuthorizeAction authorizeAction) {
        this.authorizeAction = authorizeAction;
    }


    @Override
    public void afterPropertiesSet(){
        System.out.println("AuthServerFilter Initializing");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) {
        authorizeAction.authorize(httpServletRequest,httpServletResponse);
    }
}
