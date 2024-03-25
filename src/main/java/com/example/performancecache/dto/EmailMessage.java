package com.example.performancecache.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EmailMessage {

    String to;  // 받는사람 >?
    String title;   // 메일 제목
    String content; // 이메일 내용
}
