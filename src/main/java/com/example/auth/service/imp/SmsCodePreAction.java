package com.example.auth.service.imp;

import com.example.auth.model.WapperRequest;
import com.example.auth.service.AbstractPreAction;
import com.example.auth.service.RedisUtil;
import lombok.Data;
import org.springframework.util.AntPathMatcher;

import java.util.List;

@Data
public class SmsCodePreAction extends AbstractPreAction {
    private RedisUtil redisUtil;
    private boolean on = true;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    public SmsCodePreAction(List<String> urlPropeties, RedisUtil redisUtil) {
        super(urlPropeties);
        this.redisUtil = redisUtil;
    }

    public SmsCodePreAction(String urlProperty, RedisUtil redisUtil) {
        super(urlProperty);
        this.redisUtil = redisUtil;
    }

    @Override
    public WapperRequest doPreFilter(WapperRequest wapperRequest) {
        for(String url : urlPropeties){
            if(antPathMatcher.match(url,wapperRequest.getHttpServletRequest().getRequestURI())){
                try {
                    checkSmsCode(wapperRequest);
                }catch (Exception e){
                    e.printStackTrace();
                    throw  e;
                }
            }
        }
        return wapperRequest;
    }

    private void checkSmsCode(WapperRequest wapperRequest){
       String mobile = wapperRequest.getHttpServletRequest().getParameter("mobile");
       if(mobile==null){

       }else if(!redisUtil.hasKey(mobile)){

       }else if(mobile.equals(redisUtil.getValue(mobile))){

        }
    }

}

