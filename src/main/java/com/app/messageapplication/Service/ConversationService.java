package com.app.messageapplication.Service;

import com.app.messageapplication.Dao.ConversationDao;
import com.app.messageapplication.Dao.UserDao;
import com.app.messageapplication.Entity.Conversation;
import com.app.messageapplication.Entity.ConversationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private ConversationDao conversationDao;

    public Conversation findByConversationid(String ConversationId){
        return conversationDao.findByConversationid(ConversationId);
    }

    @Transactional
    public Conversation insertMessage(String from, String to, String message){
        String ConversationId = ConversationIdService.getId(from, to);
        Conversation conversation = findByConversationid(ConversationId);
        if(conversation==null){
            return conversationDao.save(new Conversation(ConversationId,from,message));
        }
        else{
//            List<ConversationMessage> conversationMessageList = conversation.getConversationmessages();
            conversation.getConversationmessages().add(new ConversationMessage(from,message));
//            conversation.setConversationmessages(conversationMessageList);
            return conversationDao.save(conversation);
        }
    }

    public List<ConversationMessage> getConversationMessages(String from, String to){
        Conversation conversation = conversationDao.findByConversationid(ConversationIdService.getId(from,to));
        return conversation==null ? new ArrayList<>() : conversation.getConversationmessages();
    }
}
