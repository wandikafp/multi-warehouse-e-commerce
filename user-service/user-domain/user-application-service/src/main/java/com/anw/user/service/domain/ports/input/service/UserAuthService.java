package com.anw.user.service.domain.ports.input.service;

import com.anw.user.service.domain.dto.auth.UserLoginCommand;
import com.anw.user.service.domain.dto.auth.UserLoginResponse;
import com.anw.user.service.domain.dto.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserAuthService {
    UserLoginResponse login(UserLoginCommand body);
    void logout(HttpServletRequest request, HttpServletResponse response);
    UserResponse getSession(HttpServletRequest request);
}
