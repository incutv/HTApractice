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



/*
초기에 만들었던 코드

문법을 모르는게 많음;

    @Override
    public int compareTo(Notice n1 , Notice n2) {
        // n1객체 조회수 , n2객체 조회수
        // 1 = n1 > n2
        // -1 = n1 < n2
        // 0  = n1 == n2
        int result = Integer.compare(n1.getViews(), n2.getViews());
        if ( result != 0 ) {
            return  result;
        }
        // return Integer.compare(n1.getCreateDate() , n2.getCreateDate());
        return n1.getCreateDate().compareTo(n2.getCreateDate());
    }
*/


    @Override
    public int compareTo(Notice object) {
        int result = Integer.compare(object.getViews(),this.views);
        if (result != 0 ) {
            return result;
        }
        return object.getCreateDate().compareTo(this.getCreateDate());
    }


}
