package com.anw.user.service.domain;

import com.anw.user.service.domain.dto.register.RegisterUserCommand;
import com.anw.user.service.domain.dto.register.RegisterUserResponse;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.event.UserRegisteredEvent;
import com.anw.user.service.domain.mapper.UserDataMapper;
import com.anw.user.service.domain.ports.output.message.UserRegisteredMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCommandHandler {
    private final UserDataMapper userDataMapper;
    private final UserHelper userHelper;
    private final UserRegisteredMessagePublisher userRegisteredMessagePublisher;
    public RegisterUserResponse registerUser(RegisterUserCommand registerUserCommand) {
        User user = userDataMapper.registerUserCommandToUser(registerUserCommand);
        UserRegisteredEvent userRegisteredEvent = userHelper.persistRegisterUser(user);
        log.info("user is registered with id: {}", userRegisteredEvent.getUser().getId().getValue());
        userRegisteredMessagePublisher.publish(userRegisteredEvent);
        return userDataMapper.userToRegisterUserResponse(userRegisteredEvent.getUser());
    }
}
