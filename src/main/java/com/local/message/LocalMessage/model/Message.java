package com.local.message.LocalMessage.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String messageContent;
    private Long userId;
    private Long fromUser;
    private LocalDate date;
    private LocalTime time;

    public Message(Long id, String messageContent, Long userId, Long fromUsed, LocalDate date, LocalTime time) {
        this.id = id;
        this.messageContent = messageContent;
        this.userId = userId;
        this.fromUser = fromUsed;
        this.date = date;
        this.time = time;
    }

    public Message() {
    }

    public Long getFromUser() {
        return fromUser;
    }

    public void setFromUser(Long fromUsed) {
        this.fromUser = fromUsed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
