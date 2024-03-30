package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.mapper.NoticeReadMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeReadMapper noticeReadMapper;

    @Autowired
    public NoticeServiceImpl(NoticeReadMapper noticeReadMapper) {
        this.noticeReadMapper = noticeReadMapper;
    }

    @Override
    public List<Notice> getAllNotices() {
        return noticeReadMapper.findAll();
    }

    @Override
    @Cacheable(value = "top10Views")
    public List<Notice> getTop10Views() {
        List<Notice> topNotices = noticeReadMapper.findTop10Views();

        // 공지사항을 조회수로 내림차순 정렬하고, 조회수가 같을 경우 생성일로 내림차순 정렬
        topNotices.sort(Comparator.comparing(Notice::getViews)
                .thenComparing(Notice::getCreateDate)
                .reversed());

        return topNotices;
    }
}





