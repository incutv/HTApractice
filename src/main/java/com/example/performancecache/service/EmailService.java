package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);

    @Scheduled(cron = "0 0 18 * * ?")
    @SchedulerLock(name = "ScheduledTask_run")
    public void makeEmail() {
        CompletableFuture.runAsync(() -> {
            try {
                List<Notice> bestViewedNotices = noticeService.getBestViewedNotices();
                String title = "Top 10 Notice";
                String content = bestViewedNotices.toString();
                sendMail(setFrom, toMail, title, content);
                System.out.println("task1: "+ LocalDateTime.now());
            } catch (Exception e) {
                LOGGER.error("오류: ", e.getMessage());
            }
        });
    }

    @Scheduled(cron = "0 0 18 * * ?")
    @SchedulerLock(name = "ScheduledTask_run")
    public void makeEmail2() {
        List<Notice> bestViewedNotices = noticeService.getBestViewedNotices();
        String title = "Top 10 Notice";
        String content = bestViewedNotices.toString();
        sendMail(setFrom, toMail, title, content);

        System.out.println("task2: "+ LocalDateTime.now());
    }

    private void sendMail(String setFrom, String toMail, String title, String content) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content);
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("이메일 발송 오류", e);
        }
    }
}
