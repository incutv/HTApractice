package com.example.performancecache.dto;

import java.time.LocalDateTime;
import lombok.Data;
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
