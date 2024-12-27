package com.anw.user.service.application.rest;

import com.anw.user.service.domain.dto.auth.UserLoginCommand;
import com.anw.user.service.domain.dto.auth.UserLoginResponse;
import com.anw.user.service.domain.dto.user.UserResponse;
import com.anw.user.service.domain.ports.input.service.UserAuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> userlogin(
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid @RequestBody UserLoginCommand userLoginCommand) {
        log.info("User login processing: ");
        UserLoginResponse userLoginResponse = userAuthService.login(userLoginCommand);
        log.info("User login done");

        return ResponseEntity.ok(userLoginResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getSession(HttpServletRequest request) {
        log.info("Retrieve current user data: ");
        return ResponseEntity.ok(userAuthService.getSession(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        userAuthService.logout(request, response);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/csrf")
    public ResponseEntity<?> csrf() {
        return ResponseEntity.ok().build();
    }
}
