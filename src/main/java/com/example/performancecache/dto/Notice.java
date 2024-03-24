package com.example.performancecache.dto;

import lombok.Data;

import java.time.LocalDateTime;
import lombok.Getter;

@Data
@Getter
public class Notice {
    private long id;
    private String title;
    private String content;
    private String who;

    private int views;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
