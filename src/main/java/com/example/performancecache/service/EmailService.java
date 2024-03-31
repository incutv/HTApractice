package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@PropertySource(value={"classpath:application.properties"})
public class EmailService {

    private final JavaMailSender emailSender;
    private final NoticeService noticeService;

    @Value("${spring.mail.username}")
    private String toMail;

    @Value("${spring.mail.from}")
    private String setFrom;

    @Scheduled(cron = "0 0 18 * * ?")
    public void makeEmail() throws MessagingException {
        List<Notice> bestViewedNotices = noticeService.getBestViewedNotices();
        String title = "Top 10 Notice";
        String content = bestViewedNotices.toString();
        sendMail(setFrom, toMail, title, content);
    }

    private void sendMail(String setFrom, String toMail, String title, String content)  throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(setFrom);
        helper.setTo(toMail);
        helper.setSubject(title);
        helper.setText(content);
        emailSender.send(message);
    }
}
