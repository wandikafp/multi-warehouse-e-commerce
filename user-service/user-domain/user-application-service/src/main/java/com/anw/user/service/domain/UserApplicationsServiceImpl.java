package com.anw.user.service.domain;

import com.anw.domain.dto.PagedResponse;
import com.anw.domain.util.JwtUtil;
import com.anw.user.service.domain.dto.user.*;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.ports.input.service.UserApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserApplicationsServiceImpl implements UserApplicationService {
    private final UserCommandHandler userCommandHandler;
    private final JwtUtil jwtUtil;
    @Override
    public UserRegisterResponse create(UserRegisterCommand userRegisterCommand) {
        return userCommandHandler.registerUser(userRegisterCommand);
    }

    @Override
    public UserResponse update(UserUpdateCommand userRegisterCommand) {
        return userCommandHandler.update(userRegisterCommand);
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
    public UserResponse updatePassword(UserUpdatePasswordCommand userUpdatePasswordCommand) {
        return userCommandHandler.updatePassword(userUpdatePasswordCommand);
    }

    @Override
    public UserResponse updateProfilePicture(MultipartFile file) {
        return userCommandHandler.uploadProfilePicture(file);
    }

    @Override
    public PagedResponse<UserBaseResponse> getUsers(int page, int size) {
        return userCommandHandler.getUsers(page, size);
    }
}
