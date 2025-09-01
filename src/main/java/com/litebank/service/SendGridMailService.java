package com.litebank.service;

import com.litebank.dtos.request.EmailNotificationRequest;
import com.litebank.dtos.response.EmailNotificationResponse;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class SendGridMailService implements EmailNotificationService{

    @Value("${mail.api.key}")
    private String apiKey;

    @Value("${mail.api.email}")
    private String apiEmail;



    @Override
    public EmailNotificationResponse notifyBy(EmailNotificationRequest notificationRequest) throws IOException {

        Email from = new Email(apiEmail);
        Email to = new Email(notificationRequest.getRecipient());
        Content content = new Content("text/plain", notificationRequest.getMailBody());
        Mail mail = new Mail(from, notificationRequest.getSubject(), to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            request.addHeader("", "");
            Response response = sg.api(request);
            log.info("response:: {}, {}, {}", response.getStatusCode(), response.getHeaders(), response.getBody());
            EmailNotificationResponse emailNotificationResponse = new EmailNotificationResponse();
            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300)
                emailNotificationResponse.setStatus(true);
            else emailNotificationResponse.setStatus(false);
            return emailNotificationResponse;
        } catch (IOException ex) {
            throw ex;
        }
    }
}
