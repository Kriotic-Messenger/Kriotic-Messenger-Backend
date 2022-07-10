package com.app.messageapplication.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ChatMessage {

    String message;

    public ChatMessage(String message) {
        this.message = message;
    }

    public ChatMessage() {
    }
}
