package com.app.messageapplication.Entity;

import lombok.Data;

@Data
public class PrivateChatMessage {
    String from;
    String to;
    String message;

    public PrivateChatMessage(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public PrivateChatMessage() {
    }
}
