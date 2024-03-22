package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.mapper.NoticeReadMapper;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{


    private final NoticeReadMapper noticeReadMapper;


    @Override
    public List<Notice> getAllNotices() {
        return noticeReadMapper.findAll();
    }

    @Override
    @Cacheable(value = "BestCacheView")
    @Transactional
    public List<Notice> getTop10() {
        List<Notice> top10 = noticeReadMapper.bestView();

        // 데이터 정렬
        Collections.sort(top10);

        return top10;

//        return  noticeReadMapper.bestView();
    }

    @Override
    @Cacheable(value = "BestCacheView")
    @Transactional
    public List<Notice> getCompareTop10() {
        List<Notice> compareTop10 = noticeReadMapper.compareBestView();
        Collections.sort(compareTop10);
        return compareTop10;
    }
}
