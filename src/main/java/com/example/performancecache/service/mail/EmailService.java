package com.example.performancecache.service.mail;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.service.NoticeService;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final  JavaMailSender javaMailSender;
    private final NoticeService noticeService;

    // application.properties 값을 직접 주입받기
    @Value("${spring.mail.username}")
    private String recipientEmail;  // 받는사람 이메일주소 to

    @Value("${email.sender.email}")
    private String senderEmail; // 보내는사람 이메일 주소from

    /*
       SimpleMessage - 간단한 텍스트
       MimeMessage   - 텍스트 , 파일 이것저것
    */

    // 기본 메일 보내기
    public void sendMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setFrom(senderEmail);
        helper.setTo(recipientEmail);
        helper.setSubject("제목 부분");
        helper.setText("내용 부분");

        javaMailSender.send(mimeMessage);
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
        try{
            javaMailSender.send(topMessage);
        } catch (MailException ex) {
            log.error("어..음..이메일 실패했습니다",ex);
        }
    }

    @Scheduled(cron = "0 31 23 * * *")
    public void sendMailAtScheduleTime() {

        List<Notice> noticeList = noticeService.getCompareTop10();
        StringBuilder emailContent = new StringBuilder();

        for (Notice notice : noticeList) {
            emailContent.append(notice != null ? notice.toString() : "").append("\n");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("rltjs9694@gmail.com");
        message.setSubject("일정시간에 메일 보내기");
        message.setText(emailContent.toString());

        javaMailSender.send(message);

    }
}
