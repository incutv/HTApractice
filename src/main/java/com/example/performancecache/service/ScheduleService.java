package com.example.performancecache.service;

import com.example.performancecache.dto.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleService {

    @Autowired
    private EmailService emailService;

    @Value("${email.sender.email}")
    private String senderEmail;

    @Value("${email.sender.name}")
    private String senderName;

    @Value("${username}")
    private String recipientEmail;

    @Value("${email.title.notice}")
    private String subjectNotice;

    @Scheduled(cron = "0 0 18 * * *")
    public void sendNoticeContentAutomatically() {
        String content = emailService.createNoticeContent();
        EmailDetails emailDetails = new EmailDetails(senderEmail, senderName, recipientEmail, subjectNotice, content);
        emailService.sendEmail(emailDetails);
    }
}
