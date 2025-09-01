package com.litebank.dtos.request;


import com.litebank.model.AccountType;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateAccountRequest {

        private String name;
        private String username;
        private String password;
        private AccountType accountType;

}
