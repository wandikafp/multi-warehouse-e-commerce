package com.anw.user.service.domain.entity;

import com.anw.domain.entity.BaseEntity;
import com.anw.domain.valueobject.Role;
import com.anw.domain.valueobject.UserId;
import com.anw.domain.valueobject.WarehouseId;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class PasswordResetToken extends BaseEntity<Long> {
    private String token;
    private boolean emailSent = false;
    private LocalDateTime expiresAt;
    private User user;

    public PasswordResetToken(User user) {
        this.user = user;
        this.token = RandomStringUtils.random(6, false, true);
    }

    @Builder
    public PasswordResetToken(Long id,
                String token,
                boolean emailSent,
                LocalDateTime expiresAt,
                User user) {
        super.setId(id);
        this.token = token;
        this.emailSent = emailSent;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public void onEmailSent() {
        this.emailSent = true;
        this.expiresAt = LocalDateTime.now().plusMinutes(10);
    }
}
