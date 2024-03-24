package com.example.performancecache.controller;

import VO.EmailSender;
import com.example.performancecache.dto.Notice;
import com.example.performancecache.service.NoticeService;
import net.sf.ehcache.search.expression.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/bestViews")
    public ResponseEntity<Object> bestViews() {
        List<Notice> notices = noticeService.bestViews();
        sendEmailWithTopNotices(notices);
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    //@Scheduled(cron = "0 0 18 * * ?")
    public void sendTop10ViewsEmail(){
        List<Notice> top10Views = noticeService.bestViews();
        sendEmailWithTopNotices(top10Views);
    }


    private void sendEmailWithTopNotices(List<Notice> notices) {
        StringBuilder emailContent = new StringBuilder();
        for(Notice notice : notices){
            emailContent.append(notice.getTitle()).append("\n");
        }

        try {
            EmailSender.sendEmail("메일 제목", emailContent.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
