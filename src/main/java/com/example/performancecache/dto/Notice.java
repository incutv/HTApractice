package com.example.performancecache.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notice implements Comparable<Notice> {
    private long id;
    private String title;
    private String content;
    private String who;

    private int views;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public int compareTo(Notice o){
        if(this.views != o.views)
            return o.views - this.views;
        else
            return o.createDate.compareTo(this.createDate);
    }
}
