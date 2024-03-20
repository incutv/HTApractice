package com.example.performancecache.email;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    private EmailService emailService;
    private NoticeService service;

    @Autowired
    public EmailController(EmailService emailService, NoticeService service) {
        this.emailService = emailService;
        this.service = service;
    }

    @GetMapping("/send")
    public String sendEmail() {
        String from = "noreply@aaa.com";
        String to = "rbska1234@naver.com";
        String subject = "Best Top 10 Views";
        List<Notice> notices = service.getBestViews();


        emailService.sendEmail(from, to, subject, notices);

        return "email";
    }
}
