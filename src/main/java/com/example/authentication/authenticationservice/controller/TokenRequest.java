package com.example.authentication.authenticationservice.controller;

import com.fasterxml.jackson.annotation.JsonCreator;

public class TokenRequest {
    private String token;

    @JsonCreator
    public TokenRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
