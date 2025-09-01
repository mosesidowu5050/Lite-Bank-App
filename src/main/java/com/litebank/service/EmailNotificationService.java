package com.litebank.service;

import com.litebank.dtos.request.EmailNotificationRequest;
import com.litebank.dtos.response.EmailNotificationResponse;

import java.io.IOException;

public interface EmailNotificationService {

    EmailNotificationResponse notifyBy(EmailNotificationRequest notificationRequest) throws IOException;

}
