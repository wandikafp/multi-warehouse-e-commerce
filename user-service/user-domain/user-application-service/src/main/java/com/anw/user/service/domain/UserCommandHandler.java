package com.anw.user.service.domain;

import com.anw.user.service.domain.dto.login.UserLoginCommand;
import com.anw.user.service.domain.dto.login.UserLoginResponse;
import com.anw.user.service.domain.dto.register.UserRegisterCommand;
import com.anw.user.service.domain.dto.register.UserRegisterResponse;
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
    public UserRegisterResponse registerUser(UserRegisterCommand userRegisterCommand) {
        User user = userDataMapper.userRegisterCommandToUser(userRegisterCommand);
        UserRegisteredEvent userRegisteredEvent = userHelper.persistRegisterUser(user);
        log.info("user is registered with id: {}", userRegisteredEvent.getUser().getId().getValue());
        userRegisteredMessagePublisher.publish(userRegisteredEvent);
        return userDataMapper.userToUserRegisterResponse(userRegisteredEvent.getUser());
    }

    public UserLoginResponse loginUser(UserLoginCommand userLoginCommand) {
        User requestingUser = userDataMapper.userLoginCommandToUser(userLoginCommand);
        return userHelper.processingLogin(requestingUser);
    }
}
