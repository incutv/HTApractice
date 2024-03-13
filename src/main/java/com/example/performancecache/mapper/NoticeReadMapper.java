package com.example.performancecache.mapper;

import com.example.performancecache.dto.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface NoticeReadMapper {
    public List<Notice> findAll();
}
