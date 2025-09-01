package com.litebank.repository;

import com.litebank.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
class TransactionRepositoryTest {

        @Autowired
        private TransactionRepository transactionRepository;


        @Test
        void retrieveTransactionByAccountNumber() {
            String accountNumber = "123456789";
            Pageable pageable = PageRequest.of(0, 5);
            Page<Transaction> transactions = transactionRepository.readByAccountNumber(accountNumber, pageable);
            assertThat(transactions).isNotNull();
            assertThat(transactions.getContent().size()).isEqualTo(5);
        }


}