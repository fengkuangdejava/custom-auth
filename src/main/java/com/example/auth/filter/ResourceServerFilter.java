package com.example.auth.filter;

import com.example.auth.service.IAuthenticateAction;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceServerFilter extends OncePerRequestFilter implements InitializingBean {
    private IAuthenticateAction authenticateAction;
    private List<String> whiteList = new ArrayList<>();
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public void afterPropertiesSet(){
        System.out.println("ResourceServerFilter Initializing");
    }

    public ResourceServerFilter addWhiteProperties(List<String> urls){
        this.whiteList.addAll(urls);
        return this;
    }

    public ResourceServerFilter addWhiteProperty(String url){
        this.whiteList.add(url);
        return this;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
            for(String whiteUrl: whiteList){
                if(antPathMatcher.match(whiteUrl,httpServletRequest.getRequestURI())){
                    filterChain.doFilter(httpServletRequest,httpServletResponse);
                    return;
                }
            }
        authenticateAction.authenticate(httpServletRequest,httpServletResponse,filterChain);
    }
}
