package com.litebank.service;

import com.litebank.dtos.request.EmailNotificationRequest;
import com.litebank.dtos.response.EmailNotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final EmailNotificationService emailNotificationService;

        @Override
        public EmailNotificationResponse notifyBy(EmailNotificationRequest notificationRequest) throws IOException {

            return emailNotificationService.notifyBy(notificationRequest);
        }
}
