package com.anw.user.service.domain.ports.input.service;

import com.anw.domain.dto.PagedResponse;
import com.anw.user.service.domain.dto.auth.UserLoginCommand;
import com.anw.user.service.domain.dto.auth.UserLoginResponse;
import com.anw.user.service.domain.dto.user.*;
import jakarta.validation.Valid;

public interface UserApplicationService {
    UserRegisterResponse create(@Valid UserRegisterCommand userRegisterCommand);
    UserRegisterResponse update(@Valid UserUpdateCommand userRegisterCommand);
    void verifyEmail(String code);
    void forgotPassword(String email);
    void resetPassword(UserUpdatePasswordCommand userUpdatePasswordCommand);
    UserRegisterResponse updatePassword(@Valid UserUpdatePasswordCommand userRegisterCommand);
    PagedResponse<UserBaseResponse> getUsers(int page, int size);

}
