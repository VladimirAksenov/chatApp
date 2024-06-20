package com.primeacademy.chatapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MessageDTO {
    private String senderUsername;
    private List<String> receiverUsernames;
    private String content;

}
