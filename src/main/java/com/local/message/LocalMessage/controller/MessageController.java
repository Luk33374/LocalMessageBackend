package com.local.message.LocalMessage.controller;

import com.local.message.LocalMessage.dto.MessageDTO;
import com.local.message.LocalMessage.model.Message;
import com.local.message.LocalMessage.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/getMessageWithId/{id}")
    public Message[] getMessage(@PathVariable String id){
        return messageService.getMessage(id);
    }

    @GetMapping("/getMessageWithUserId/{id}")
    public Message[] getMessageByUserId(@PathVariable String id){
        return messageService.getMessageByUser(id);
    }

    @PostMapping("/setMessage")
    public Long setMessage(@RequestBody MessageDTO messageBody){
        return messageService.saveMessage(messageBody);
    }
}
