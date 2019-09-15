package com.example.auth.model;

public class DefaultAuthResult implements AuthResult {
    private ClientDetail cilentDetail;
    private UserAuth userAuth;

    public DefaultAuthResult(ClientDetail cilentDetail, UserAuth userAuth) {
        this.cilentDetail = cilentDetail;
        this.userAuth = userAuth;
    }

    public ClientDetail getCilentDetail() {
        return cilentDetail;
    }

    public void setCilentDetail(ClientDetail cilentDetail) {
        this.cilentDetail = cilentDetail;
    }

    public UserAuth getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }
}
