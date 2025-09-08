package com.litebank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String username;
    private String password;
    private String accountNumber;
    private AccountType accountType;
    private Set<Authority> authorities;

    public Account () {
        this.authorities = new HashSet<>();
        authorities.add(Authority.ACCOUNT);
    }

}
