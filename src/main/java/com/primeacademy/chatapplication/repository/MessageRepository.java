package com.primeacademy.chatapplication.repository;

import com.primeacademy.chatapplication.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}