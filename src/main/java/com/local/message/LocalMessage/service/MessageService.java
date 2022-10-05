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
    private int byUserIdIndex=0;
    private int fromUserIndex=0;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message[] getMessage(String id){
        return messageRepository.getMessageById(Long.parseLong(id));
    }

    public Message[] getMessageByUser(String id){
        int messageArrayLength;
        Message[] allUserMessages;
        Message[] byUserId = messageRepository.getMessageByUserId(Long.parseLong(id));
        Message[] fromUser = messageRepository.getMessageByFromUser(Long.parseLong(id));
        messageArrayLength=byUserId.length+fromUser.length;
        allUserMessages= new Message[messageArrayLength];
        byUserIdIndex=0;
        fromUserIndex=0;
        for(int i=0;i<messageArrayLength;i++){
            if(fromUser.length>fromUserIndex){
                if(byUserId.length>byUserIdIndex)allUserMessages[i]=getOlderMessage(fromUser[fromUserIndex],byUserId[byUserIdIndex]);
                else {
                    allUserMessages[i]=fromUser[fromUserIndex];
                    fromUserIndex++;
                }
            }
            else {
                allUserMessages[i] = byUserId[byUserIdIndex];
                byUserIdIndex++;
            }
        }
        return allUserMessages;
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

    private Message getOlderMessage(Message fromUser, Message byUser){
        if(fromUser.getDate().isBefore(byUser.getDate())){
            fromUserIndex++;
            return fromUser;
        }
        else if(fromUser.getDate().isEqual(byUser.getDate())){
            if(fromUser.getTime().isBefore(byUser.getTime())){
                fromUserIndex++;
                return fromUser;
            }
            else {
                byUserIdIndex++;
                return byUser;
            }
        }
        else {
            byUserIdIndex++;
            return byUser;
        }
    }
}
