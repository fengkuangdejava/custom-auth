package com.example.auth.service.imp;

import com.example.auth.handler.IAuthorizeFailedHandler;
import com.example.auth.handler.IAuthorizeSucessHandler;
import com.example.auth.model.ActionManage;
import com.example.auth.model.ClientDetail;
import com.example.auth.model.UserAuth;
import com.example.auth.model.WapperRequest;
import com.example.auth.service.ClientService;
import com.example.auth.service.IAuthorizeAction;
import com.example.auth.service.AbstractLoginAction;
import com.example.auth.service.AbstractPreAction;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.MethodNotAllowedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 默认的授权实现
 *
 * @author liting
 * */
@Data
@Slf4j
public class DefaultAuthorizeAction implements IAuthorizeAction {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    private List<String> loginUrls = new ArrayList<>();
    /**
     * 前置动作处理器 可集成短信验证码校验 图片验证码校验 等等其它校验
     * */
    private ActionManage<AbstractPreAction> preActionActionManage;
    /**
     * 登录动作处理器 可集成密码登录,手机登录，第三方登录，授权登录等
     * */
    private ActionManage<AbstractLoginAction> loginActionActionManage;
    /**
     * 统一异常处理器
     * */
    private IAuthorizeFailedHandler authorizeFailedHandler;
    /**
     * 登录成功处理器
     */
    private IAuthorizeSucessHandler authorizeSucessHandler;

    private ClientService clientServiceImpl;
    @Override
    public void authorize(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        WapperRequest wapperRequest = new WapperRequest(httpServletRequest,httpServletResponse);
        for(String loginUrl : loginUrls){
                if(antPathMatcher.match(loginUrl,httpServletRequest.getRequestURI())){
                    if("post".equals(httpServletRequest.getMethod().toLowerCase())) {
                        try {
                             doLogin(wapperRequest);
                        }catch (Exception e){
                            authorizeFailedHandler.dealFailed(wapperRequest,e);
                        }
                        return;
                    }else {
                        authorizeFailedHandler.dealFailed(wapperRequest,new MethodNotAllowedException(httpServletRequest.getMethod(), Sets.newHashSet(HttpMethod.POST)));
                    }
                }
            }
    }
    void doLogin(WapperRequest wapperRequest) throws IOException {
        for(AbstractPreAction preAction:preActionActionManage.getList()){
            try {
                for(String preUrl: preAction.getUrlPropeties()){
                    if(antPathMatcher.match(preUrl,wapperRequest.getHttpServletRequest().getRequestURI())){
                        wapperRequest = preAction.doPreFilter(wapperRequest);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                log.error("登录前置处理异常");
                authorizeFailedHandler.dealFailed(wapperRequest,e);
            }
        }
        UserAuth user = null;
        ClientDetail clientDetail = clientServiceImpl.checkClient(wapperRequest.getHttpServletRequest());
        for(AbstractLoginAction loginAction:loginActionActionManage.getList()){
            try {
                 user = loginAction.doLogin(wapperRequest);
                if(wapperRequest.isAuthorized()){
                    break;
                }
            }catch (Exception e){
                authorizeFailedHandler.dealFailed(wapperRequest,e);
            }
        }
        authorizeSucessHandler.dealSucess(clientDetail,user,wapperRequest);
    }

    public static void main(String[] args) {
        DefaultAuthorizeAction defaultAuthorizeAction = new DefaultAuthorizeAction();
        String s = "/usr/login/**";
        String ss = "/usr/login";
        System.out.println(defaultAuthorizeAction.antPathMatcher.match(s,ss));
    }
}
