package com.anw.user.service.domain;

import com.anw.domain.dto.PagedResponse;
import com.anw.user.service.domain.dto.user.*;
import com.anw.user.service.domain.ports.input.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApplicationsServiceImpl implements UserApplicationService {
    private final UserCommandHandler userCommandHandler;
    @Override
    public UserRegisterResponse create(UserRegisterCommand userRegisterCommand) {
        return userCommandHandler.registerUser(userRegisterCommand);
    }

    @Override
    public UserRegisterResponse update(UserUpdateCommand userRegisterCommand) {
        return null;
    }

    @Override
    public void verifyEmail(String code) {
        userCommandHandler.verifyEmail(code);
    }

    @Override
    public void forgotPassword(String email) {
        userCommandHandler.forgotPassword(email);
    }

    @Override
    public void resetPassword(UserUpdatePasswordCommand userUpdatePasswordCommand) {
        userCommandHandler.resetPassword(userUpdatePasswordCommand);
    }

    @Override
    public UserRegisterResponse updatePassword(UserUpdatePasswordCommand userRegisterCommand) {
        return null;
    }

    @Override
    public PagedResponse<UserBaseResponse> getUsers(int page, int size) {
        return userCommandHandler.getUsers(page, size);
    }
}
