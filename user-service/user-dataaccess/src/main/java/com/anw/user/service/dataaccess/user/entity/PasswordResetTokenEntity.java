package com.anw.user.service.dataaccess.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_token")
@Getter
@NoArgsConstructor
public class PasswordResetTokenEntity extends AbstractEntity {
    private String token;
    private boolean emailSent = false;
    private LocalDateTime expiresAt;

    @ManyToOne
    private UserEntity user;

    public PasswordResetTokenEntity(
        String token,
        boolean emailSent,
        LocalDateTime expiresAt,
        UserEntity user
    ) {
        this.token = token;
        this.emailSent = emailSent;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
