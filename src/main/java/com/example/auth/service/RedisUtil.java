package com.example.auth.service;

public interface RedisUtil {
    boolean hasKey(String mobile);

    String getValue(String mobile);
}
