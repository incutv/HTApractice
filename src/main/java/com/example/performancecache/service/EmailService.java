package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Scheduled(cron = "0 0 18 * * *")
    public void sendEmailAutomatically(List<Notice> notices) {
        sendEmail(notices);
    }

    public void sendEmail(List<Notice> notices) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < notices.size(); i++) {
            int num = i + 1;
            Notice notice = notices.get(i);
            content.append("\n < HOT Notices " + num + "> \n" + notice.getContent() + "\n");
        }

        try {
            helper.setFrom("noreply@gmail.com", "noreply");
            helper.setTo("hinote444@gmail.com");
            helper.setSubject("Hot Notices!");
            helper.setText(content.toString());
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
