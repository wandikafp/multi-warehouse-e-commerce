package com.anw.user.service.domain.ports.input.service;

import com.anw.user.service.domain.dto.register.RegisterUserCommand;
import com.anw.user.service.domain.dto.register.RegisterUserResponse;
import jakarta.validation.Valid;

public interface UserApplicationService {
    RegisterUserResponse registerUser(@Valid RegisterUserCommand registerUserCommand);
}
