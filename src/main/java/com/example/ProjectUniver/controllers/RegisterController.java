package com.example.ProjectUniver.controllers;

import com.example.ProjectUniver.dto.*;
import com.example.ProjectUniver.service.OrganizationService;
import com.example.ProjectUniver.service.OrganizationServiceImpl;
import com.example.ProjectUniver.service.UserService;
import com.example.ProjectUniver.service.UserServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Tag(name = "Регистрация", description = "Все методы для работы с регистрацией, авторизацией")
public class RegisterController {
    @Autowired
    UserServiceImp actorServiceImp;
    @Autowired
    UserService actorService;
    @Autowired
    OrganizationServiceImpl organizationServiceImpl;
    @Autowired
    OrganizationService organizationService;

    @PostMapping("auth/login")
    @Operation(summary = "Авторизация")
    public ResponseEntity<JwtResponseDto> authUser(@RequestBody LoginDto loginDto, HttpServletResponse response) {

        JwtResponseDto jwtResponse = actorService.login(loginDto);
        String jwt = jwtResponse.getToken();
        String cookieValue = "token=" + jwt + "; Path=/; Secure; HttpOnly";
        response.setHeader("Set-Cookie", cookieValue);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        //return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @PostMapping("auth/registrationuser")
    @Operation(summary = "Регистрация")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody RegistrationDto registrationDto) {
        return new ResponseEntity<>(actorService.registration(registrationDto), HttpStatus.OK);

    }
    @PostMapping("auth/registration")
    @Operation(summary = "Регистрация")
    public ResponseEntity<MessageResponse> registerOrganization(@RequestBody RegistrationOrganizationDto registrationOrganizationDto) {
        return new ResponseEntity<>(actorService.registration(registrationOrganizationDto), HttpStatus.OK);

    }
}