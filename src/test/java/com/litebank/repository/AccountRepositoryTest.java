package com.litebank.repository;

import com.litebank.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void findByAccountNumber(){
        Optional<Account> accountNumber = accountRepository.findByAccountNumber("123456789");
        Account customerAccount = accountNumber.orElseThrow(RuntimeException::new);
        assertNotNull(customerAccount);
    }

    @Test
    void canFindByAccountUsername() {
        Account account = accountRepository.findByUsername("mdempire")
                .orElseThrow(() -> new RuntimeException("Account not found"));
        assertNotNull(account);
        assertEquals("mdempire", account.getUsername());
    }
}
