package com.anw.user.service.domain.ports.input.service;

import com.anw.user.service.domain.dto.auth.UserLoginCommand;
import com.anw.user.service.domain.dto.auth.UserLoginResponse;
import com.anw.user.service.domain.dto.user.UserUpdateCommand;
import com.anw.user.service.domain.dto.user.UserUpdatePasswordCommand;
import com.anw.user.service.domain.dto.user.UserRegisterCommand;
import com.anw.user.service.domain.dto.user.UserRegisterResponse;
import jakarta.validation.Valid;

public interface UserApplicationService {
    UserRegisterResponse create(@Valid UserRegisterCommand userRegisterCommand);
    UserRegisterResponse update(@Valid UserUpdateCommand userRegisterCommand);
    void verifyEmail(String code);
    void forgotPassword(String email);
    void resetPassword(UserUpdatePasswordCommand userUpdatePasswordCommand);
    UserRegisterResponse updatePassword(@Valid UserUpdatePasswordCommand userRegisterCommand);

}
