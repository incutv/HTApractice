package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private NoticeService noticeService;

    @Scheduled(cron = "0 0 18 * * *")
    public void sendEmailAutomatically() {
        List<Notice> notices = noticeService.getNoticesByViews();
        emailService.sendEmail(notices);
    }
}
