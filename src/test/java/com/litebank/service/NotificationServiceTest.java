package com.litebank.service;

import com.litebank.dtos.request.EmailNotificationRequest;
import com.litebank.dtos.response.EmailNotificationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;


    @Test
    public void testSendEmailNotification() throws IOException {
        EmailNotificationRequest notificationRequest = new EmailNotificationRequest();
        notificationRequest.setRecipient("becaw65821@chaublog.com");
        notificationRequest.setSubject("Test Subject");
        notificationRequest.setMailBody("Test Body");
        EmailNotificationResponse response =  notificationService.notifyBy(notificationRequest);
        assertThat(response).isNotNull();
         assertThat(response.isStatus()).isTrue();

    }

    @Test
    public void testSendEmailNotificationWithInvalidEmail() throws IOException {
        EmailNotificationRequest notificationRequest = new EmailNotificationRequest();
        notificationRequest.setRecipient("i.mosesbabs@gmail.com");
        notificationRequest.setSubject("Test Subject");
        notificationRequest.setMailBody("Test Body");
        notificationService.notifyBy(notificationRequest);
    }
}
