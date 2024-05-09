package com.example.authentication.authenticationservice.service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authentication.authenticationservice.model.Account;
import com.example.authentication.authenticationservice.model.LoginBody;
import com.example.authentication.authenticationservice.repo.AccountRepository;

@Service
public class AuthService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account signup(Account account) {
        account.setHashedPassword(passwordEncoder.encode(account.getHashedPassword()));
        if (validateEmail(account.getEmail())) {
            return accountService.save(account);
        }
        return null;
    }

    public String login(LoginBody request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email, request.hashedPassword));
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        Account account = accountRepository.findByEmail(request.email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        if (!passwordEncoder.matches(request.hashedPassword, account.getHashedPassword())) {
            throw new IllegalArgumentException("Invalid email or password.");
        }
        return jwtService.generateToken(account);
    }

    private boolean validateEmail(String email) {
        Pattern reg = Pattern.compile("^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$");
        Matcher matcher = reg.matcher(email);
        return matcher.matches();
    }

    public boolean validateToken(String token) {
        return jwtService.isTokenValid(token);
    }

    public UUID extractUuid(String token) {
        return jwtService.extractUuid(token);
    }

    public String extractEmail(String token) {
        return jwtService.extractEmail(token);
    }

    public String extractName(String token) {
        return jwtService.extractName(token);
    }

    public String extractRole(String token) {
        return jwtService.extractRole(token);
    }

    public String extractImage(String token) {
        return jwtService.extractImage(token);
    }

    public String extractNationality(String token) {
        return jwtService.extractNationality(token);
    }

    public String extractNationalId(String token) {
        return jwtService.extractNationalId(token);
    }

    public String extractGender(String token) {
        return jwtService.extractGender(token);
    }

    public int extractLoyalityPoints(String token) {
        return jwtService.extractLoyalityPoints(token);
    }
}
