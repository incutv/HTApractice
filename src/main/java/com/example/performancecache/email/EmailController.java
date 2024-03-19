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

        String to = "01066230616a@gmail.com";
        String subject = "Best Top 10 Views";
        List<Notice> notices = service.getBestViews();


        emailService.sendEmail(to, subject, notices);

        return "email";
    }
}
