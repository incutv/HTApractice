package com.example.performancecache.email;

import com.example.performancecache.dto.Notice;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {


    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;}

    @Scheduled(cron = "0 0 18 * * *")
    public void sendEmail(String from, String to, String subject, List<Notice> notices) {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);


        try {
            helper.setFrom(new InternetAddress(from));
            helper.setTo("rbska1234@naver.com");
            helper.setSubject("bestTop10Views");

            StringBuilder textBuilder = new StringBuilder();
            for (Notice notice : notices) {
                textBuilder.append(notice.toString()).append("\n");
            }
            helper.setText(textBuilder.toString());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }
}
