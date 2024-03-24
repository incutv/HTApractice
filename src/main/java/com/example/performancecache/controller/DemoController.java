package com.example.performancecache.controller;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.service.NoticeService;
import net.sf.ehcache.search.expression.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demo")
public class DemoController {
    private NoticeService noticeService;

    public DemoController(NoticeService noticeService){
        this.noticeService=noticeService;
    }

    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        List<Notice> notices = noticeService.getAllNotices();
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    @GetMapping("/bestViews")
    public ResponseEntity<Object> bestViews() {
        List<Notice> notices = noticeService.bestViews();
        return new  ResponseEntity<>(notices, HttpStatus.OK);
    }
}
