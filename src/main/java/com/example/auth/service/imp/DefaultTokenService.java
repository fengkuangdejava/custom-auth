package com.example.auth.service.imp;

import com.example.auth.model.AccessToken;
import com.example.auth.model.AuthResult;
import com.example.auth.model.RefreshToken;
import com.example.auth.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class DefaultTokenService implements TokenService {

    @Override
    public AccessToken createAccesToken(AuthResult authResult) {
        return null;
    }

    @Override
    public RefreshToken createRefreshToken(String uuid) {
        return null;
    }

    @Override
    public AccessToken refreshToken(String refreshToken) {
        return null;
    }

    @Override
    public AccessToken getAccessToken(String accessToken) {
        return null;
    }
}
