package com.example.auth.model;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface AccessToken {
    String BEARER_TYPE = "Bearer";
    String ACCESS_TOKEN = "access_token";
    String TOKEN_TYPE = "token_type";
    String EXPIRES_IN = "expires_in";
    String REFRESH_TOKEN = "refresh_token";
    String SCOPE = "scope";

    Map<String, Object> getAdditionalInformation();

    Set<String> getScope();

    RefreshToken getRefreshToken();

    String getTokenType();

    boolean isExpired();

    Date getExpiration();

    int getExpiresIn();

    String getValue();
}
