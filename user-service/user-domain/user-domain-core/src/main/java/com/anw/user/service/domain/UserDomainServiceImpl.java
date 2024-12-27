package com.anw.user.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.event.UserRegisteredEvent;
import com.anw.user.service.domain.util.ApplicationContextProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.anw.domain.DomainConstants.UTC;

@Slf4j
@RequiredArgsConstructor
public class UserDomainServiceImpl implements UserDomainService {

    @Override
    public UserRegisteredEvent registerUser(User user,
                                                   DomainEventPublisher<UserRegisteredEvent> userCreatedEventDomainEventPublisher) {
        user.initializeUser();
        PasswordEncoder passwordEncoder = ApplicationContextProvider.bean(PasswordEncoder.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("User with id: {} is initiated", user.getId().getValue());
        return new UserRegisteredEvent(user, ZonedDateTime.now(ZoneId.of(UTC)), userCreatedEventDomainEventPublisher);
    }

    @Override
    public boolean validateUpdateProfileImage(byte[] file, String fileName) {
        return isFileSizeValid(file) && isImageFile(fileName);
    }

    private boolean isFileSizeValid(byte[] file) {
        return file.length <= 1048576;
    }

    private boolean isImageFile(String fileName) {
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif");
    }
}
