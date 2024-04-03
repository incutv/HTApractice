package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.mapper.NoticeReadMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
    @Cacheable(value = "NoticeReadMapper.findAll")
    public List<Notice> getAllNotices() {
        return noticeReadMapper.findAll();
    }

    @Override
    @Cacheable(value = "NoticeReadMapper.findBestViewedNotices")
    public List<Notice> getBestViewedNotices() {
        List<Notice> bestViewedNotices = noticeReadMapper.findBestViewedNotices();
        Collections.sort(bestViewedNotices);
        return bestViewedNotices;
    }

}
