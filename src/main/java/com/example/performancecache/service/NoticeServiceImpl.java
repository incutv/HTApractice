package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.mapper.NoticeReadMapper;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
    @Cacheable(value = "notices")
    public List<Notice> getNoticesByViews() {
        List<Notice> notices = noticeReadMapper.findHotNotices();
        Collections.sort(notices);

        return notices;
    }
}
