package com.primeacademy.chatapplication.controller;

import com.primeacademy.chatapplication.dto.UserRegistrationDTO;
import com.primeacademy.chatapplication.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Username is already taken")
    })
    @PostMapping("/register")
    public String registerUser(@RequestBody UserRegistrationDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @Operation(summary = "Login a user")
    @PostMapping("/login")
    public void login() {
    }
}
