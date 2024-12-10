package com.anw.user.service.domain;

import com.anw.user.service.domain.dto.login.UserLoginCommand;
import com.anw.user.service.domain.dto.login.UserLoginResponse;
import com.anw.user.service.domain.dto.register.UserRegisterCommand;
import com.anw.user.service.domain.dto.register.UserRegisterResponse;
import com.anw.user.service.domain.ports.input.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApplicationsServiceImpl implements UserApplicationService {
    private final UserCommandHandler userCommandHandler;
    @Override
    public UserRegisterResponse userRegistration(UserRegisterCommand userRegisterCommand) {
        return userCommandHandler.registerUser(userRegisterCommand);
    }
    @Override
    public UserLoginResponse userLogin(UserLoginCommand userLoginCommand) {
        return userCommandHandler.loginUser(userLoginCommand);
    }
}
