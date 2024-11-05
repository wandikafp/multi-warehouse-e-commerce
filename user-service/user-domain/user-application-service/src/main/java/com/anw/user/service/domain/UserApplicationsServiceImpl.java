package com.anw.user.service.domain;

import com.anw.user.service.domain.dto.register.RegisterUserCommand;
import com.anw.user.service.domain.dto.register.RegisterUserResponse;
import com.anw.user.service.domain.ports.input.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApplicationsServiceImpl implements UserApplicationService {
    private final UserCommandHandler userCommandHandler;
    @Override
    public RegisterUserResponse registerUser(RegisterUserCommand registerUserCommand) {
        return userCommandHandler.registerUser(registerUserCommand);
    }
}
