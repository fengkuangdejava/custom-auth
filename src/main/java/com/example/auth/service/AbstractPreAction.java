package com.example.auth.service;

import com.example.auth.model.WapperRequest;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AbstractPreAction {
    protected List<String> urlPropeties =new ArrayList<>();

    public AbstractPreAction(List<String> urlPropeties) {
        this.urlPropeties = urlPropeties;
    }
    public AbstractPreAction(String urlProperty) {
        this.urlPropeties.add(urlProperty);
    }


    public abstract WapperRequest doPreFilter(WapperRequest wapperRequest);
    public AbstractPreAction addProperty(String url){
        urlPropeties.add(url);
        return this;
    }

    public AbstractPreAction addProperties( List<String> urlPropeties){
        urlPropeties.addAll(urlPropeties);
        return this;
    }
}
