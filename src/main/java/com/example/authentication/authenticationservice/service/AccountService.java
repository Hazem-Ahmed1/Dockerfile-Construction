package com.example.authentication.authenticationservice.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.authentication.authenticationservice.model.Account;
import com.example.authentication.authenticationservice.repo.AccountRepository;

@Service
public class AccountService implements UserDetailsService {

  @Autowired
  private AccountRepository accountRepository;

  public Account loadUserByUsername(String email) {
    return accountRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  public Account loadUserByUUID(UUID uuid) {
    return accountRepository.findByUuid(uuid)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  public Account save(Account newAccount) {
    if (newAccount.getId() == null) {
      newAccount.setCreatedAt(LocalDateTime.now());
    }

    newAccount.setUpdatedAt(LocalDateTime.now());
    return accountRepository.save(newAccount);
  }
}
