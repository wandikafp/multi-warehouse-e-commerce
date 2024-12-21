package com.anw.user.service.dataaccess.user.entity;

import com.anw.dataaccess.entity.AbstractLongEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class UserConnectedAccountEntity extends AbstractLongEntity {
    private String provider;
    private String providerId;
    private LocalDateTime connectedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public UserConnectedAccountEntity(String provider, String providerId, UserEntity user) {
        this.provider = provider;
        this.providerId = providerId;
        this.connectedAt = LocalDateTime.now();
        this.user = user;
    }
}
