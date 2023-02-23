package com.example.ProjectUniver.controllers;

import com.example.ProjectUniver.dto.JwtResponseDto;
import com.example.ProjectUniver.dto.LoginDto;
import com.example.ProjectUniver.dto.MessageResponse;
import com.example.ProjectUniver.dto.RegistrationDto;
import com.example.ProjectUniver.service.UserService;
import com.example.ProjectUniver.service.UserServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Регистрация", description = "Все методы для работы с регистрацией, авторизацией")
public class RegisterController {
    @Autowired
    UserServiceImp actorServiceImp;
    @Autowired
    UserService actorService;

    @PostMapping("auth/login")
    @Operation(summary = "Авторизация")
    public ResponseEntity<JwtResponseDto> authUser(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(actorService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping("auth/registration")
    @Operation(summary = "Регистрация")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody RegistrationDto registrationDto) {
        return new ResponseEntity<>(actorService.registration(registrationDto), HttpStatus.OK);

    }
}