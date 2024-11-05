package com.anw.user.service.domain;

import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.event.UserRegisteredEvent;
import com.anw.user.service.domain.ports.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserHelper {
    private final UserDomainService userDomainService;
    private final UserRepository userRepository;
    @Transactional
    public UserRegisteredEvent persistRegisterUser(User User) {
        UserRegisteredEvent UserCreatedEvent = userDomainService.validateAndRegisterUser(User, null);
        saveUser(User);
        log.info("initialize User with id: {}", User.getId().getValue());
        return UserCreatedEvent;
    }

    private void saveUser(User user) {
        User userResult = userRepository.save(user);
        log.info("User is saved with id: {}", userResult.getId().getValue());
    }
}
