package com.primeacademy.chatapplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    @JsonIgnore
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private boolean active;

    public UserDTO(Long id, String username, boolean active) {
        this.id = id;
        this.username = username;
        this.active = active;
    }
}
