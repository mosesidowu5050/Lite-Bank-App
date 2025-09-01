package com.litebank.dtos.request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailNotificationRequest {

    private String recipient;
    private String subject;
    private String mailBody;
}
