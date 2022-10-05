package com.local.message.LocalMessage.service;

import com.local.message.LocalMessage.dto.MessageDTO;
import com.local.message.LocalMessage.model.Message;
import com.local.message.LocalMessage.reposiotory.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message[] getMessage(String id){
        return messageRepository.getMessageById(Long.parseLong(id));
    }

    public Message[] getMessageByUser(String id){
        return messageRepository.getMessageByUserId(Long.parseLong(id));
    }

    public Long saveMessage(MessageDTO messageDTO){
        Message message=new Message();
        message.setTime(LocalTime.now());
        message.setDate(LocalDate.now());
        message.setMessageContent(messageDTO.getMessageContent());
        message.setFromUser(new Long(messageDTO.getFromUser()));
        message.setUserId(new Long(messageDTO.getUserId()));
        messageRepository.save(message);
        return message.getId();
    }
}
