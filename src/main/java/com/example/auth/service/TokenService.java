package com.example.auth.service;

import com.example.auth.model.AccessToken;
import com.example.auth.model.AuthResult;
import com.example.auth.model.RefreshToken;

import java.util.Map;

public interface TokenService {
    AccessToken createAccesToken(AuthResult authResult);
    RefreshToken createRefreshToken(String uuid);
    AccessToken refreshToken(String refreshToken);
    AccessToken getAccessToken(String accessToken);
}
