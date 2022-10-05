package com.local.message.LocalMessage.reposiotory;

import com.local.message.LocalMessage.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message[] getMessageById(Long id);
    Message[] getMessageByUserId(Long id);
    Message[] getMessageByFromUser(Long id);
}
