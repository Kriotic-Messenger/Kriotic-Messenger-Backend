package com.app.messageapplication.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConversationMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String messagefrom;
    String message;

    public ConversationMessage(String messageFrom,String message){
        this.messagefrom = messageFrom;
        this.message = message;
    }
}
