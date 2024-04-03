package com.example.performancecache.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EmailInfo {

    String senderEmail;      // 보내는 사람 이메일
    String senderName;       // 보내는 사람 이름
    String recipientEmail;   // 받는 사람 이메일
    String recipientName;    // 받는 사람 이름
    String title;
    String content;
}
