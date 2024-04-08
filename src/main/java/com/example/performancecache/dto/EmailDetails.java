package com.example.performancecache.dto;

import lombok.Getter;

@Getter
public class EmailDetails {
    private String senderEmail;
    private String senderName;
    private String recipientEmail;
    private String recipientName;
    private String title;
    private String content;

    public EmailDetails() {
    }

    public EmailDetails(String senderEmail, String senderName, String recipientEmail, String recipientName,
                        String title,
                        String content) {
        this.senderEmail = senderEmail;
        this.senderName = senderName;
        this.recipientEmail = recipientEmail;
        this.recipientName = recipientName;
        this.title = title;
        this.content = content;
    }

    public EmailDetails(String senderEmail, String senderName, String recipientEmail, String title, String content) {
        this.senderEmail = senderEmail;
        this.senderName = senderName;
        this.recipientEmail = recipientEmail;
        this.title = title;
        this.content = content;
    }

    public EmailDetails(String senderEmail, String recipientEmail, String title, String content) {
        this.senderEmail = senderEmail;
        this.recipientEmail = recipientEmail;
        this.title = title;
        this.content = content;
    }
}
