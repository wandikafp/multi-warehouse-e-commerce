package com.anw.user.service.application.rest;

import com.anw.user.service.domain.dto.register.RegisterUserCommand;
import com.anw.user.service.domain.dto.register.RegisterUserResponse;
import com.anw.user.service.domain.ports.input.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/user", produces = "application/json")
@RequiredArgsConstructor
public class UserController {
    private final UserApplicationService userApplicationService;

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserCommand registerUserCommand) {
        log.info("Registering user: ");
        RegisterUserResponse registerUserResponse = userApplicationService.registerUser(registerUserCommand);
        log.info("User registered with id: {}", registerUserResponse);
        return ResponseEntity.ok(registerUserResponse);
    }
}
