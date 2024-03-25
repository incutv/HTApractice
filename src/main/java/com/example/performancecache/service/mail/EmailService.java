package com.example.performancecache.service.mail;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.service.NoticeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final  JavaMailSender javaMailSender;

    private final NoticeService noticeService;

    // 메일보내기
    public void simpleSendMail() {

        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject("구글에서 네이버로");
        smm.setTo("rltjs3563@naver.com");
        smm.setFrom("noreply@gmail.com");
        smm.setText("Test 메일내용 입니당");

        javaMailSender.send(smm);
    }

    public void top10SendMail(List<Notice> noticeList){
        StringBuilder emailContent = new StringBuilder();

        for (Notice notice : noticeList) {
            emailContent.append(notice != null ? notice.toString() : "").append("\n");
        }

        SimpleMailMessage topMessage = new SimpleMailMessage();
        topMessage.setTo("rltjs9694@gmail.com");
        topMessage.setFrom("noreply@gmail.com");
        topMessage.setSubject("상위 조회수 10개 목록");
        topMessage.setText(emailContent.toString());

        javaMailSender.send(topMessage);
    }

    @Scheduled(cron = "0 15 17 * * *")
    public void sendMailAtScheduleTime() {

        List<Notice> noticeList = noticeService.getCompareTop10();
        StringBuilder emailContent = new StringBuilder();

        for (Notice notice : noticeList) {
            emailContent.append(notice != null ? notice.toString() : "").append("\n");
        }
        System.out.println("#######   이메일 보내기 작동하는지 확인 ##########");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("rltjs9694@gmail.com");
        message.setSubject("일정시간에 메일 보내기");
        message.setText(emailContent.toString());

        System.out.println("######### 이메일 보내기 작동하는지 확인 2 ##########");
        javaMailSender.send(message);

        System.out.println("#### 이메일 보내기 작동하는지 확인 3  #######");
    }
}
