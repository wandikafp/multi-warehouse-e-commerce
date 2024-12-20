package com.anw.user.service.domain.mapper;

import com.anw.domain.valueobject.Role;
import com.anw.user.service.domain.dto.auth.UserLoginCommand;
import com.anw.user.service.domain.dto.user.UserRegisterCommand;
import com.anw.user.service.domain.dto.user.UserRegisterResponse;
import com.anw.user.service.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDataMapper {
    public User userRegisterCommandToUser(UserRegisterCommand userRegisterCommand) {
        return User.builder()
                .email(userRegisterCommand.getEmail())
                .password(userRegisterCommand.getPassword())
                .fullName(userRegisterCommand.getFullName())
                .role(Optional.ofNullable(userRegisterCommand.getRole())
                        .map(Role::valueOf)
                        .orElse(Role.CUSTOMER))
                .warehouseId(userRegisterCommand.getWarehouseId())
                .build();
    }

    public User userLoginCommandToUser(UserLoginCommand userLoginCommand) {
        return User.builder()
                .email(userLoginCommand.getEmail())
                .password(userLoginCommand.getPassword())
                .build();
    }

    public UserRegisterResponse userToUserRegisterResponse(User user) {
        return UserRegisterResponse.builder()
                .id(user.getId().getValue().toString())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .build();
    }
}
