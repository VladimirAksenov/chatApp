package com.primeacademy.chatapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private boolean active;

    public UserDTO(Long id, String username, boolean active) {
        this.id = id;
        this.username = username;
        this.active = active;
    }
}
