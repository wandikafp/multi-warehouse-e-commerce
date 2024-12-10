package com.anw.user.service.domain.mapper;

import com.anw.domain.valueobject.Role;
import com.anw.user.service.domain.dto.login.UserLoginCommand;
import com.anw.user.service.domain.dto.login.UserLoginResponse;
import com.anw.user.service.domain.dto.register.UserRegisterCommand;
import com.anw.user.service.domain.dto.register.UserRegisterResponse;
import com.anw.user.service.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {
    public User userRegisterCommandToUser(UserRegisterCommand userRegisterCommand) {
        return User.builder()
                .username(userRegisterCommand.getUserName())
                .password(userRegisterCommand.getPassword())
                .firstName(userRegisterCommand.getFirstName())
                .lastName(userRegisterCommand.getLastName())
                .role(Role.valueOf(userRegisterCommand.getRole()))
                .warehouseId(userRegisterCommand.getWarehouseId())
                .build();
    }

    public User userLoginCommandToUser(UserLoginCommand userLoginCommand) {
        return User.builder()
                .username(userLoginCommand.getUserName())
                .password(userLoginCommand.getPassword())
                .build();
    }

    public UserRegisterResponse userToUserRegisterResponse(User user) {
        return UserRegisterResponse.builder()
                .userName(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public UserLoginResponse userToUserLoginResponse(User user) {
        return UserLoginResponse.builder()
                .userName(user.getUsername())
                .build();
    }
}
