package com.app.messageapplication.controller;

import com.app.messageapplication.Entity.ChatMessage;
import com.app.messageapplication.Service.RestMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.pattern.PathPattern;

@RestController
@CrossOrigin("*")
public class RestMessageController {

    @Autowired
    private RestMessageService restMessageService;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody ChatMessage chatMessage){
        restMessageService.sendMessageToAll(chatMessage);
    }

    @PostMapping("/send-to-user/{username}")
    public void sentToUser(@PathVariable(value = "username") String username, @RequestBody ChatMessage chatMessage){
        restMessageService.sendToUser(username, chatMessage);
    }

}
