package com.example.performancecache.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Notice implements Comparable<Notice> {
    private long id;
    private String title;
    private String content;
    private String who;

    private int views;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @Override
    public int compareTo(Notice otherNotice) {
        if (this.views != otherNotice.views) {
            return otherNotice.views - this.views;
        }

        return otherNotice.createDate.compareTo(this.createDate);
    }
}
