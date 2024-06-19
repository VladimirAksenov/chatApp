package com.primeacademy.chatapplication.repository;

import com.primeacademy.chatapplication.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}