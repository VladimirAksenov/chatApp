package com.primeacademy.chatapplication.dto;

import com.primeacademy.chatapplication.model.ChatRoom;
import com.primeacademy.chatapplication.model.Message;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private boolean active;
    private Set<ChatRoom> chatRooms;
    private Set<Message> sentMessages;
    private Set<Message> receivedMessages;

    public UserDTO(Long id, String username, boolean active, Set<ChatRoom> chatRooms, Set<Message> sentMessages, Set<Message> receivedMessages) {
        this.id = id;
        this.username = username;
        this.active = active;
        this.chatRooms = chatRooms;
        this.sentMessages = sentMessages;
        this.receivedMessages = receivedMessages;
    }
}
