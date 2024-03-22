package com.example.performancecache.controller.mail;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.service.NoticeService;
import com.example.performancecache.service.mail.EmailService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/simple")
    public String simpleSendMail() {
        emailService.simpleSendMail();

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
