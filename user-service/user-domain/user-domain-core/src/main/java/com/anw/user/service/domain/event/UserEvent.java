package com.anw.user.service.domain.event;

import com.anw.domain.event.DomainEvent;
import com.anw.user.service.domain.entity.User;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public abstract class UserEvent implements DomainEvent<User> {
    private final User user;
    private final ZonedDateTime createdAt;
    public UserEvent(User user, ZonedDateTime createdAt) {
        this.user = user;
        this.createdAt = createdAt;
    }
}
