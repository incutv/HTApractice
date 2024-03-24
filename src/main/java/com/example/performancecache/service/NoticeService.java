package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;


public interface NoticeService {
    List<Notice> getAllNotices();

    List<Notice> bestViews();

}
