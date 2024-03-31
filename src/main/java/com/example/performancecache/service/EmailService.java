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

    @Scheduled(cron = "0 * * * * ?") // 매분마다 실행
    @SchedulerLock(name = "ScheduledTask_run")
    public void makeEmail() throws MessagingException {
        List<Notice> bestViewedNotices = noticeService.getBestViewedNotices();
        String title = "Top 10 Notice";
        String content = bestViewedNotices.toString();
        sendMail(setFrom, toMail, title, content);

        System.out.println("task1: "+ LocalDateTime.now());
    }

    @Scheduled(cron = "0 * * * * ?")
    @SchedulerLock(name = "ScheduledTask_run")
    public void makeEmail2() throws MessagingException {
        List<Notice> bestViewedNotices = noticeService.getBestViewedNotices();
        String title = "Top 10 Notice";
        String content = bestViewedNotices.toString();
        sendMail(setFrom, toMail, title, content);

        System.out.println("task2: "+ LocalDateTime.now());
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
