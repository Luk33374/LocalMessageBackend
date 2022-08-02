package com.local.message.LocalMessage.dto;


public class MessageDTO {
    private String messageContent;
    private int userId;
    private int fromUser;

    public String getMessageContent() {
        return messageContent;
    }

    public int getUserId() {
        return userId;
    }

    public int getFromUser() {
        return fromUser;
    }
}
