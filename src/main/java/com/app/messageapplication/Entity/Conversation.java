package com.app.messageapplication.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String conversationid;

    @OneToMany(cascade = CascadeType.ALL)
    List<ConversationMessage> conversationmessages;

    public Conversation(String conversationid) {
        this.conversationid = conversationid;
        this.conversationmessages = new ArrayList<ConversationMessage>();
    }

    public Conversation(String conversationid, String messageFrom ,String firstMessage){
        this.conversationid = conversationid;
        this.conversationmessages = new ArrayList<ConversationMessage>();
        this.conversationmessages.add(new ConversationMessage(messageFrom,firstMessage));
    }
}
