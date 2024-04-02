package com.example.performancecache.controller.mail;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.service.NoticeService;
import com.example.performancecache.service.mail.EmailService;
import java.util.List;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mail")
public class EmailController {


    private final EmailService emailService;
    private final NoticeService noticeService;

    @Value("${spring.mail.username}")
    private String to;


    @GetMapping("/simple")
    public String simpleSendMail() {
        try {
            emailService.sendMail();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "email/simpleEmail";
    }




    @GetMapping("/top10")
    public String top10View(Model model) {
        List<Notice> noticeList = noticeService.getCompareTop10();
        emailService.top10SendMail(noticeList);

       // model.addAttribute("message", "이메일 완료");

        return "email/top10";
    }

}
