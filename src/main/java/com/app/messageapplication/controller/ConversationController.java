package com.app.messageapplication.controller;

import com.app.messageapplication.Entity.ConversationMessage;
import com.app.messageapplication.Entity.PrivateChatMessage;
import com.app.messageapplication.Service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @GetMapping("/getConversation/{from}/{to}")
    public List<ConversationMessage> getConversationMessages(@PathVariable(value = "from") String from, @PathVariable(value = "to") String to){
        return conversationService.getConversationMessages(from, to);
    }

    @PostMapping("/sendMessage")
    public void insertMessage(@RequestBody PrivateChatMessage privateChatMessage){
        conversationService.insertMessage(privateChatMessage.getFrom(), privateChatMessage.getTo(), privateChatMessage.getMessage());
    }

}
