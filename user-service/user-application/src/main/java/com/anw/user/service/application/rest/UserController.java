package com.anw.user.service.application.rest;

import com.anw.user.service.domain.dto.login.UserLoginCommand;
import com.anw.user.service.domain.dto.login.UserLoginResponse;
import com.anw.user.service.domain.dto.register.UserRegisterCommand;
import com.anw.user.service.domain.dto.register.UserRegisterResponse;
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
@RequestMapping(value = "/api/user", produces = "application/json")
@RequiredArgsConstructor
public class UserController {
    private final UserApplicationService userApplicationService;

    @PostMapping("/auth/signup")
    public ResponseEntity<UserRegisterResponse> userRegistration(@RequestBody UserRegisterCommand userRegisterCommand) {
        log.info("Registering user: ");
        UserRegisterResponse userRegisterResponse = userApplicationService.userRegistration(userRegisterCommand);
        log.info("User registered with id: {}", userRegisterResponse);
        return ResponseEntity.ok(userRegisterResponse);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserLoginResponse> userlogin(@RequestBody UserLoginCommand userLoginCommand) {
        log.info("User login processing: ");
        UserLoginResponse userLoginResponse = userApplicationService.userLogin(userLoginCommand);
        log.info("User registered with id: {}", userLoginResponse);
        return ResponseEntity.ok(userLoginResponse);
    }
}
