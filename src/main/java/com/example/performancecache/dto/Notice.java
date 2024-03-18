package com.example.performancecache.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notice {
    private long id;
    private String title;
    private String content;
    private String who;

    private int view;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}