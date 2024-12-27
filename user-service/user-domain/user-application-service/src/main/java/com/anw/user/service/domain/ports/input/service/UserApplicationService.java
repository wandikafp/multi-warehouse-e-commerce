package com.anw.user.service.domain.ports.input.service;

import com.anw.domain.dto.PagedResponse;
import com.anw.user.service.domain.dto.user.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

public interface UserApplicationService {
    UserRegisterResponse create(@Valid UserRegisterCommand userRegisterCommand);
    UserResponse update(@Valid UserUpdateCommand userRegisterCommand);
    void verifyEmail(String code);
    void forgotPassword(String email);
    void resetPassword(UserUpdatePasswordCommand userUpdatePasswordCommand);
    UserResponse updatePassword(@Valid UserUpdatePasswordCommand userRegisterCommand);
    UserResponse updateProfilePicture(MultipartFile file);
    PagedResponse<UserBaseResponse> getUsers(int page, int size);

}
