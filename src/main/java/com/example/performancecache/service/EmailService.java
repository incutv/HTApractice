package com.example.performancecache.service;

import VO.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.SpringTemplateLoader;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleEmail(){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("메일 제목입니다");
        message.setTo("ftsand@naver.com");
        message.setText("메일 내용 구간");
    }
}
