package com.primeacademy.chatapplication.controller;

import com.primeacademy.chatapplication.dto.MessageDTO;
import com.primeacademy.chatapplication.dto.UserDTO;
import com.primeacademy.chatapplication.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody MessageDTO messageDto) {
        return chatService.sendMessage(messageDto);
    }

    @GetMapping("/active-users")
    public List<UserDTO> getActiveUsers() {
        return chatService.getActiveUsers();
    }
}
