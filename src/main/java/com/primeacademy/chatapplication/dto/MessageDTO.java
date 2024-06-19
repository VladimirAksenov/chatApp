package com.primeacademy.chatapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private String senderUsername;
    private String receiverUsername;
    private String content;

    public MessageDTO(String senderUsername, String receiverUsername, String content) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.content = content;
    }
}