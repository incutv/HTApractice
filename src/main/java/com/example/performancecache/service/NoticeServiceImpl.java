package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.mapper.NoticeReadMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

    private NoticeReadMapper noticeReadMapper;

    public NoticeServiceImpl(NoticeReadMapper noticeReadMapper) {
        this.noticeReadMapper = noticeReadMapper;
    }

    @Override
    public List<Notice> getAllNotices() {
        return noticeReadMapper.findAll();
    }

    @Override
    @Cacheable(value = "top10Views")
    @Transactional
    public List<Notice> getTop10Views() {
        return noticeReadMapper.findTop10Views();
    }


}



