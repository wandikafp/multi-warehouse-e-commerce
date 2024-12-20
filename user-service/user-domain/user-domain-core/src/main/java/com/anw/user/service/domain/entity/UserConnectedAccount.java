package com.anw.user.service.domain.entity;

import com.anw.domain.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class UserConnectedAccount extends BaseEntity<Long> {
    private String provider;
    private String providerId;
    private LocalDateTime connectedAt;
    private User user;
}
