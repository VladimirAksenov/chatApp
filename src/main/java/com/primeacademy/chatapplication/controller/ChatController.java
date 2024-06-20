package com.primeacademy.chatapplication.controller;

import com.primeacademy.chatapplication.dto.MessageDTO;
import com.primeacademy.chatapplication.dto.UserDTO;
import com.primeacademy.chatapplication.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Operation(summary = "Send a message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message sent successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Sender or receiver not found")
    })
    @PostMapping("/send")
    public String sendMessage(@RequestBody MessageDTO messageDto) {
        return chatService.sendMessage(messageDto);
    }

    @Operation(summary = "Get list of active users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of active users retrieved successfully")
    })
    @GetMapping("/active-users")
    public List<UserDTO> getActiveUsers() {
        return chatService.getActiveUsers();
    }
}
