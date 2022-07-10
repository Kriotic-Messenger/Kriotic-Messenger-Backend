package com.app.messageapplication.Dao;

import com.app.messageapplication.Entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationDao extends JpaRepository<Conversation,Integer> {

    public Conversation findByConversationid(String conversationid);
}
