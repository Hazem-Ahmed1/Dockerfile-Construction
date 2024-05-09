package com.example.authentication.authenticationservice.model;

public class LoginBody {

    public String email;
    public String hashedPassword;

    public LoginBody(String email, String hashedPassword) {
        this.email = email;
        this.hashedPassword = hashedPassword;
    }    
}
