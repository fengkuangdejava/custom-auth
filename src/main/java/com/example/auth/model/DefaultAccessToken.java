package com.example.auth.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.*;

public class DefaultAccessToken  implements AccessToken{
    private static final long serialVersionUID = 914967629530462926L;
    private String value;
    private Date expiration;
    private String tokenType;
    private RefreshToken refreshToken;
    private Set<String> scope;
    private Map<String, Object> additionalInformation;

    public DefaultAccessToken(String value) {
        this.tokenType = "Bearer".toLowerCase();
        this.additionalInformation = Collections.emptyMap();
        this.value = value;
    }

    private DefaultAccessToken() {
        this((String)null);
    }

    public DefaultAccessToken(AccessToken accessToken) {
        this(accessToken.getValue());
        this.setAdditionalInformation(accessToken.getAdditionalInformation());
        this.setRefreshToken(accessToken.getRefreshToken());
        this.setExpiration(accessToken.getExpiration());
        this.setScope(accessToken.getScope());
        this.setTokenType(accessToken.getTokenType());
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public int getExpiresIn() {
        return this.expiration != null ? Long.valueOf((this.expiration.getTime() - System.currentTimeMillis()) / 1000L).intValue() : 0;
    }

    protected void setExpiresIn(int delta) {
        this.setExpiration(new Date(System.currentTimeMillis() + (long)delta));
    }

    @Override
    public Date getExpiration() {
        return this.expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    @Override
    public boolean isExpired() {
        return this.expiration != null && this.expiration.before(new Date());
    }

    @Override
    public String getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public RefreshToken getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public Set<String> getScope() {
        return this.scope;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && this.toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }

    public static AccessToken valueOf(Map<String, String> tokenParams) {
        DefaultAccessToken token = new DefaultAccessToken(tokenParams.get("access_token"));
        if (tokenParams.containsKey("expires_in")) {
            long expiration = 0L;

            try {
                expiration = Long.parseLong(String.valueOf(tokenParams.get("expires_in")));
            } catch (NumberFormatException var5) {
            }

            token.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L));
        }

        if (tokenParams.containsKey("refresh_token")) {
            String refresh = tokenParams.get("refresh_token");
            DefaultRefreshToken refreshToken = new DefaultRefreshToken(refresh);
            token.setRefreshToken(refreshToken);
        }

        if (tokenParams.containsKey("scope")) {
            Set<String> scope = new TreeSet();
            StringTokenizer tokenizer = new StringTokenizer(tokenParams.get("scope"), " ,");

            while(tokenizer.hasMoreTokens()) {
                scope.add(tokenizer.nextToken());
            }

            token.setScope(scope);
        }

        if (tokenParams.containsKey("token_type")) {
            token.setTokenType(tokenParams.get("token_type"));
        }

        return token;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = new LinkedHashMap(additionalInformation);
    }
}
