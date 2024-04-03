package com.example.performancecache.controller;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demo")
public class DemoController {
    private NoticeService noticeService;

    public DemoController(NoticeService noticeService){
        this.noticeService=noticeService;
    }


    @GetMapping("/view")
    public String list(Model model) {
        List<Notice> noticeList = noticeService.getAllNotices();
        model.addAttribute("noticeList",noticeList);

        return "notice/NoticeList";

    }

    @GetMapping("/bestViews")
    public ResponseEntity<Object> bestView() {
        List<Notice> bestView = noticeService.getTop10();
        return new ResponseEntity<>(bestView, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        List<Notice> notices = noticeService.getAllNotices();
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }


}
