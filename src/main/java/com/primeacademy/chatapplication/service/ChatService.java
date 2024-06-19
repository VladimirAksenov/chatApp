package com.primeacademy.chatapplication.service;

import com.primeacademy.chatapplication.dto.MessageDTO;
import com.primeacademy.chatapplication.dto.UserDTO;
import com.primeacademy.chatapplication.model.ChatRoom;
import com.primeacademy.chatapplication.model.Message;
import com.primeacademy.chatapplication.model.User;
import com.primeacademy.chatapplication.repository.ChatRoomRepository;
import com.primeacademy.chatapplication.repository.MessageRepository;
import com.primeacademy.chatapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private MessageRepository messageRepository;

    public String sendMessage(MessageDTO messageDto) {
        String senderUsername = messageDto.getSenderUsername();
        String receiverUsername = messageDto.getReceiverUsername();
        String content = messageDto.getContent();

        User sender = userRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findByUsername(receiverUsername)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Optional<ChatRoom> existingChatRoom = chatRoomRepository.findAll().stream()
                .filter(chatRoom -> chatRoom.getUsers().contains(sender) && chatRoom.getUsers().contains(receiver))
                .findFirst();

        ChatRoom chatRoom;
        if (existingChatRoom.isPresent()) {
            chatRoom = existingChatRoom.get();
        } else {
            chatRoom = new ChatRoom();
            Set<User> users = new HashSet<>();
            users.add(sender);
            users.add(receiver);
            chatRoom.setUsers(users);
            chatRoomRepository.save(chatRoom);
        }

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        message.setChatRoom(chatRoom);

        messageRepository.save(message);

        return "Message sent";
    }

    public List<UserDTO> getActiveUsers() {
        return userRepository.findAll().stream()
                .filter(User::isActive)
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.isActive()
                ))
                .collect(Collectors.toList());
    }
}
