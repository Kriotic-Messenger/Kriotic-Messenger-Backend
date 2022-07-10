package com.app.messageapplication.controller;

import com.app.messageapplication.Entity.ChatMessage;
import com.app.messageapplication.Entity.PrivateChatMessage;
import com.app.messageapplication.Service.ConversationService;
import com.app.messageapplication.Service.RestMessageService;
import com.app.messageapplication.Service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin("*")
public class MessageController {

    @Autowired
    private RestMessageService restMessageService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private UserService userService;

    Gson g = new Gson();

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public String broker(@Payload String chatMessage){
        System.out.println(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/sendPrivateMessage")
    public void privateBroker(@Payload String chatMessage){
        PrivateChatMessage privateChatMessage = g.fromJson(chatMessage,PrivateChatMessage.class);
        System.out.println(privateChatMessage);
        conversationService.insertMessage(privateChatMessage.getFrom(), privateChatMessage.getTo(), privateChatMessage.getMessage());
        userService.insertConversation(privateChatMessage.getFrom(), privateChatMessage.getTo());
        restMessageService.sendToUser(privateChatMessage);
    }

    @GetMapping("/Hello")
    public String hello(){
        return "Hello";
    }
}
