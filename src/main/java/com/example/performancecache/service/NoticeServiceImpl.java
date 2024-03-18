package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.mapper.NoticeReadMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;


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
    @Cacheable(value = "BestCacheView")
    @Transactional
    public List<Notice> getTop10() {
        return  noticeReadMapper.bestView();
    }

}
