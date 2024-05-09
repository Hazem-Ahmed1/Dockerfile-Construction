package com.example.authentication.authenticationservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authentication.authenticationservice.model.Account;
import com.example.authentication.authenticationservice.model.LoginBody;
import com.example.authentication.authenticationservice.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Account request) {
        Account newAccount = authService.signup(request);
        if(newAccount == null){
            return ResponseEntity.badRequest().body("Account not Created");
        }
        return ResponseEntity.ok("Account Created Successfully");
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginBody request) {
        return authService.login(request);
    }

    @PostMapping("/validate")
    public boolean validate(@RequestBody TokenRequest token) {
        return authService.validateToken(token.getToken());
    }

    @PostMapping("/uuid")
    public UUID tokenUuid(@RequestBody TokenRequest token) {
        return authService.extractUuid(token.getToken());
    }

    @PostMapping("/email")
    public String tokenEmail(@RequestBody TokenRequest token) {
        return authService.extractEmail(token.getToken());
    }

    @PostMapping("/name")
    public String tokenName(@RequestBody TokenRequest token) {
        return authService.extractName(token.getToken());
    }

    @PostMapping("/role")
    public String tokenRole(@RequestBody TokenRequest token) {
        return authService.extractRole(token.getToken());
    }

    @PostMapping("/image")
    public String tokenImage(@RequestBody TokenRequest token) {
        return authService.extractImage(token.getToken());
    }

    @PostMapping("/nationality")
    public String tokenNationality(@RequestBody TokenRequest token) {
        return authService.extractNationality(token.getToken());
    }

    @PostMapping("/nationalId")
    public String tokenNationalId(@RequestBody TokenRequest token) {
        return authService.extractNationalId(token.getToken());
    }

    @PostMapping("/gender")
    public String tokenGender(@RequestBody TokenRequest token) {
        return authService.extractGender(token.getToken());
    }

    @PostMapping("/loyalityPoints")
    public int tokenLoyalityPoints(@RequestBody TokenRequest token) {
        return authService.extractLoyalityPoints(token.getToken());
    }

    @PostMapping("/passenger")
    @PreAuthorize("hasRole('PASSENGER')")
    public String passenger(HttpServletRequest request) {
        return "hello passenger";
    }

    @PostMapping("/flatowner")
    @PreAuthorize("hasRole('FLATOWNER')")
    public String flatOwner(HttpServletRequest request) {
        return "hello flatowner";
    }
}
