package com.anw.user.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.event.UserRegisteredEvent;
import com.anw.user.service.domain.exception.UserDomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.anw.domain.DomainConstants.UTC;

@Slf4j
@RequiredArgsConstructor
public class UserDomainServiceImpl implements UserDomainService {
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRegisteredEvent registerUser(User user,
                                                   DomainEventPublisher<UserRegisteredEvent> userCreatedEventDomainEventPublisher) {
        user.initializeUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("User with id: {} is initiated", user.getId().getValue());
        return new UserRegisteredEvent(user, ZonedDateTime.now(ZoneId.of(UTC)), userCreatedEventDomainEventPublisher);
    }

    @Override
    public Boolean isValidLogin(User requestingUser, User savedUser) {
        return !passwordEncoder.matches(requestingUser.getPassword(), savedUser.getPassword());
    }
}
