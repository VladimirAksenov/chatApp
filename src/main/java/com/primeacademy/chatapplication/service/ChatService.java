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
        List<String> receiverUsernames = messageDto.getReceiverUsernames();
        String content = messageDto.getContent();

        User sender = userRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        // Получение всех получателей
        List<User> receivers = receiverUsernames.stream()
                .map(username -> userRepository.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("Receiver " + username + " not found")))
                .toList();

        // Включение отправителя в список участников
        Set<User> participants = new HashSet<>(receivers);
        participants.add(sender);

        // Проверка наличия комнаты чата, где участвуют только эти участники
        Optional<ChatRoom> existingChatRoom = chatRoomRepository.findAll().stream()
                .filter(chatRoom -> {
                    Set<User> chatRoomUsers = chatRoom.getUsers();
                    return chatRoomUsers.size() == participants.size() && chatRoomUsers.containsAll(participants);
                })
                .findFirst();

        ChatRoom chatRoom;
        if (existingChatRoom.isPresent()) {
            chatRoom = existingChatRoom.get();
        } else {
            chatRoom = new ChatRoom();
            chatRoom.setUsers(participants);
            chatRoomRepository.save(chatRoom);
        }

        // Создание и сохранение сообщения для каждого получателя
        for (User receiver : receivers) {
            Message message = new Message();
            message.setSender(sender);
            message.setReceiver(receiver);  // Устанавливаем receiver
            message.setContent(content);
            message.setTimestamp(LocalDateTime.now());
            message.setChatRoom(chatRoom);

            messageRepository.save(message);
        }

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
                .toList();
    }
}
