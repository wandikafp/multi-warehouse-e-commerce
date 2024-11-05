package com.anw.user.service.domain.mapper;

import com.anw.domain.valueobject.Role;
import com.anw.user.service.domain.dto.register.RegisterUserCommand;
import com.anw.user.service.domain.dto.register.RegisterUserResponse;
import com.anw.user.service.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {
    public User registerUserCommandToUser(RegisterUserCommand registerUserCommand) {
        return User.builder()
                .username(registerUserCommand.getUserName())
                .firstName(registerUserCommand.getFirstName())
                .lastName(registerUserCommand.getLastName())
                .role(Role.valueOf(registerUserCommand.getRole()))
                .warehouseId(registerUserCommand.getWarehouseId())
                .build();
    }

    public RegisterUserResponse userToRegisterUserResponse(User user) {
        return RegisterUserResponse.builder()
                .userName(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
