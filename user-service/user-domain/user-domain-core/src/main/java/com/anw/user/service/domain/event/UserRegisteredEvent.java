package com.anw.user.service.domain.event;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class UserRegisteredEvent extends UserEvent{
    private final DomainEventPublisher<UserRegisteredEvent> userCreatedEventDomainEventPublisher;
    public UserRegisteredEvent(User user,
                            ZonedDateTime createdAt,
                            DomainEventPublisher<UserRegisteredEvent> userCreatedEventDomainEventPublisher) {
        super(user, createdAt);
        this.userCreatedEventDomainEventPublisher = userCreatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        userCreatedEventDomainEventPublisher.publish(this);
    }
}
