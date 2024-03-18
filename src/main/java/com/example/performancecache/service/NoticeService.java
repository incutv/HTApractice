package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;

import java.util.List;


public interface NoticeService {
    List<Notice> getAllNotices();
    List<Notice> getNoticesByViews();

}
