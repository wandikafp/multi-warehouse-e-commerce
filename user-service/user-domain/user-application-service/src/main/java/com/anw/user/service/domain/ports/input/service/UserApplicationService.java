package com.anw.user.service.domain.ports.input.service;

import com.anw.user.service.domain.dto.login.UserLoginCommand;
import com.anw.user.service.domain.dto.login.UserLoginResponse;
import com.anw.user.service.domain.dto.register.UserRegisterCommand;
import com.anw.user.service.domain.dto.register.UserRegisterResponse;
import jakarta.validation.Valid;

public interface UserApplicationService {
    UserRegisterResponse userRegistration(@Valid UserRegisterCommand userRegisterCommand);
    UserLoginResponse userLogin(@Valid UserLoginCommand userLoginCommand);

}
