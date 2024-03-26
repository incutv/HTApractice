package com.example.performancecache.controller;

import com.example.performancecache.dto.EmailDetails;
import com.example.performancecache.dto.Notice;
import com.example.performancecache.service.EmailService;
import com.example.performancecache.service.NoticeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Value("${email.sender.email}")
    private String senderEmail;

    @Value("${email.sender.name}")
    private String senderName;

    @Value("${username}")
    private String recipientEmail;

    @Value("${email.title.notice}")
    private String subjectNotice;

    private EmailService emailService;
    private NoticeService noticeService;

    public DemoController(NoticeService noticeService, EmailService emailService) {
        this.noticeService = noticeService;
        this.emailService = emailService;
    }

    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        List<Notice> notices = noticeService.getAllNotices();
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    @GetMapping("/hot")
    public ResponseEntity<Object> findHotNotices() {
        List<Notice> notices = noticeService.sortNoticesByViewsAndCreateDate();
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    @GetMapping("/email")
    public String triggerEmail() {
        String content = emailService.createNoticeContent();
        EmailDetails emailDetails = new EmailDetails(senderEmail, senderName, recipientEmail, subjectNotice, content);
        emailService.sendEmail(emailDetails);
        return "Success!";
    }

}
