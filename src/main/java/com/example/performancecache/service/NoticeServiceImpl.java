package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.mapper.NoticeReadMapper;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;



@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService{

    private NoticeReadMapper noticeReadMapper;

    public NoticeServiceImpl(NoticeReadMapper noticeReadMapper){
        this.noticeReadMapper = noticeReadMapper;
    }

    @Override
    public List<Notice> getAllNotices() {
        return noticeReadMapper.findAll();
    }


    @Override
    @Cacheable(value = "bestViewsCache")
    public List<Notice> getBestViews() {

        List<Notice> notices = noticeReadMapper.bestViews();

        Collections.sort(notices);
        return notices;

    }
}
