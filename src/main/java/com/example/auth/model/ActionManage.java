package com.example.auth.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActionManage<T> {
    public List<T> list = new ArrayList<>();

    public ActionManage addBefore(T t, Class clazz){
        int i=0;
        for(T t1:list){
            if(t1.getClass().toString().equals(clazz.toString())){
                break;
            }
            i++;
        }
        list.add(i,t);
        return this;
    }

    public ActionManage addAfter(T t, Class clazz){
        int i=0;
        for(T t1:list){
            i++;
            if(t1.getClass().toString().equals(clazz.toString())){
                break;
            }
        }
        list.add(i,t);
        return this;
    }

    public ActionManage addAfter(T t){
        list.add(t);
        return this;
    }
}
