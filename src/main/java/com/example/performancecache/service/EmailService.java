package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${username}")
    private String recipientEmail;
    @Value("${email.sender.address}")
    private String senderAddress;
    @Value("${email.sender.name}")
    private String senderName;

    public void sendEmail(List<Notice> notices) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < notices.size(); i++) {
            int num = i + 1;
            Notice notice = notices.get(i);
            content.append("\n" + num + ". HOT Notices [" + notice.getViews() + "] \n" + notice.getContent() + "\n");
        }

        try {
            helper.setFrom(senderAddress, senderName);
            helper.setTo(recipientEmail);
            helper.setSubject("Hot Notices!");
            helper.setText(content.toString());
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("[ERROR] 이메일 전송 중 오류가 발생했습니다.");
        } catch (UnsupportedEncodingException e) {
            System.out.println("[ERROR] 이메일 주소 확인바랍니다.");
        }
    }
}
