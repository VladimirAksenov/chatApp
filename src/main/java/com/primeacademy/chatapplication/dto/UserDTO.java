package com.primeacademy.chatapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private boolean active;

    public UserDTO(Long id, String username, boolean active) {
        this.id = id;
        this.username = username;
        this.active = active;
    }
}
