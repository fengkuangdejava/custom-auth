package com.example.auth.service;

import com.example.auth.model.UserAuth;

public interface UserService {
   UserAuth findUserByName(String name);

    UserAuth findByMobile(String mobile);
}
