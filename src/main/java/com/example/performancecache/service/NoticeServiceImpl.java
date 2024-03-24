package com.example.performancecache.service;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.mapper.NoticeReadMapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.search.expression.Not;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Comparator;
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
    @Cacheable(value = "NoticeReadMapper.bestViews")
    @Transactional
    public List<Notice> bestViews() {
        List<Notice> notices = noticeReadMapper.bestViews();

        notices.sort(new Comparator<Notice>() {

            public int compare(Notice notice1, Notice notice2){
                int compareByViews = Integer.compare(notice2.getViews(), notice1.getViews());

                if(compareByViews == 0) {
                    return notice2.getCreateDate().compareTo(notice1.getCreateDate());
                }

                return compareByViews;
            }
        });
        return notices;
    }

}
