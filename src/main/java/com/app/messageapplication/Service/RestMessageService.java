package com.app.messageapplication.Service;

import com.app.messageapplication.Entity.ChatMessage;
import com.app.messageapplication.Entity.PrivateChatMessage;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RestMessageService {

    private SimpMessagingTemplate simpMessagingTemplate;

    public RestMessageService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendMessageToAll(ChatMessage chatMessage){
        simpMessagingTemplate.convertAndSend("/topic/public",chatMessage.getMessage());
    }

    public void sendToUser(String username, ChatMessage chatMessage){
        simpMessagingTemplate.convertAndSend("/topic/private/"+username,chatMessage.getMessage());
    }

    public void sendToUser(PrivateChatMessage privateChatMessage){
        simpMessagingTemplate.convertAndSend("/topic/private/"+ConversationIdService.getId(privateChatMessage.getFrom(),privateChatMessage.getTo()),
                privateChatMessage);

        simpMessagingTemplate.convertAndSend("/topic/getnotification/"+privateChatMessage.getTo(), privateChatMessage);
    }

//    public void sendToUser(PrivateChatMessage privateChatMessage){
//        simpMessagingTemplate.convertAndSend("/topic/private/"+privateChatMessage.getTo(),privateChatMessage);
//    }
}
