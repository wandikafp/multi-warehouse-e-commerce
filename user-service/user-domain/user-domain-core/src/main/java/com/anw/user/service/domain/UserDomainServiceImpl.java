package com.anw.user.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.event.UserRegisteredEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

import static com.anw.domain.DomainConstants.UTC;

@Slf4j
public class UserDomainServiceImpl implements UserDomainService {
    @Override
    public UserRegisteredEvent validateAndRegisterUser(User user,
                                                   DomainEventPublisher<UserRegisteredEvent> userCreatedEventDomainEventPublisher) {
        user.initializeUser();
        log.info("User with id: {} is initiated", user.getId().getValue());
        return new UserRegisteredEvent(user, ZonedDateTime.now(ZoneId.of(UTC)), userCreatedEventDomainEventPublisher);
    }
}
