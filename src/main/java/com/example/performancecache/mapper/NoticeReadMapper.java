package com.example.performancecache.mapper;

import com.example.performancecache.dto.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeReadMapper {
     public List<Notice> findAll();

     public List<Notice> findTop10Views();


}