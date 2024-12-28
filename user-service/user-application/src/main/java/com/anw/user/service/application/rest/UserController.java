package com.anw.user.service.application.rest;

import com.anw.domain.dto.PagedResponse;
import com.anw.user.service.domain.UserCommandHandler;
import com.anw.user.service.domain.dto.user.*;
import com.anw.user.service.domain.ports.input.service.UserApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserApplicationService userApplicationService;
    private final UserCommandHandler userCommandHandler;

    @PostMapping
    public ResponseEntity<UserRegisterResponse> userRegistration(@Valid @RequestBody UserRegisterCommand userRegisterCommand) {
        log.info("Registering user: ");
        UserRegisterResponse userRegisterResponse = userApplicationService.create(userRegisterCommand);
        log.info("User registered with id: {}", userRegisterResponse);
        return ResponseEntity.ok(userRegisterResponse);
    }

    @GetMapping("/verify-email")
    public RedirectView verifyEmail(@RequestParam String token) {
        userApplicationService.verifyEmail(token);
        return new RedirectView("{}/auth/login");
    }
    
    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody UserForgotPasswordCommand req) {
        userApplicationService.forgotPassword(req.getEmail());
        return ResponseEntity.ok().build();
    }
    
    @PatchMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(
            @Valid @RequestBody UserUpdatePasswordCommand requestDTO) {
        userApplicationService.resetPassword(requestDTO);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserUpdateCommand request) {
        UserResponse user = userApplicationService.update(request);
        return ResponseEntity.ok(user);
    }
    
    @PatchMapping("/password")
    public ResponseEntity<UserResponse> updatePassword(
            @Valid @RequestBody UserUpdatePasswordCommand requestDTO) {
        UserResponse user = userApplicationService.updatePassword(requestDTO);
        return ResponseEntity.ok(user);
    }
    
    @PatchMapping("/{id}/profile-picture")
    public ResponseEntity<UserResponse> updateProfilePicture(
            @PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        UserResponse user = userApplicationService.updateProfilePicture(file);
        return ResponseEntity.ok(user);
    }

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PagedResponse<UserBaseResponse>> getUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PagedResponse<UserBaseResponse> users = userApplicationService.getUsers(page, size);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<String> testing(@PathVariable UUID id) {
        userCommandHandler.sendEmailVerificationTest(id);
        return ResponseEntity.ok("success");
    }

}

