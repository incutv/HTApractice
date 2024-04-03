package com.example.performancecache.controller;

import com.example.performancecache.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.MessagingException;

@Controller
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/email/send")
    public ResponseEntity<String> main() throws MessagingException {
        emailService.makeEmail();
        return new ResponseEntity<>("메일 전송 완료", HttpStatus.OK);
    }
}
