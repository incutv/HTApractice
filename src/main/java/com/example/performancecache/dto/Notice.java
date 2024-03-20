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

    @Override
    public int compareTo(Notice n) {
        // 조회수로 정렬
        int viewComparison = Integer.compare(n.views, this.views);

        if (viewComparison != 0) {

            // 조회수가 다를 경우 조회수를 기준으로 정렬
            return viewComparison;
        } else {
            // 조회수가 같을 경우 생성 날짜를 기준으로 정렬
            return this.createDate.compareTo(n.createDate);
        }


    }
}

