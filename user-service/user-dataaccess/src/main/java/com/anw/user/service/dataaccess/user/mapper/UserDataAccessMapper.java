package com.anw.user.service.dataaccess.user.mapper;

import com.anw.domain.valueobject.BaseId;
import com.anw.domain.valueobject.Role;
import com.anw.domain.valueobject.UserId;
import com.anw.domain.valueobject.WarehouseId;
import com.anw.user.service.dataaccess.user.entity.UserEntity;
import com.anw.user.service.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDataAccessMapper {
    public User userEntityToUser(UserEntity userEntity) {
        return User.builder()
                .userId(new UserId(userEntity.getId()))
                .username(userEntity.getUsername())
                .fullName(userEntity.getFullName())
                .password(userEntity.getPassword())
                .role(Role.valueOf(userEntity.getRole()))
                .warehouseId(new WarehouseId(userEntity.getWarehouseId()))
                .build();
    }

    public UserEntity userToUserEntity(User user) {
        return UserEntity.builder()
                .id(user.getId().getValue())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .password(user.getPassword())
                .role(user.getRole().toString())
                .warehouseId(
                        Optional.ofNullable(user.getWarehouseId())
                                .map(BaseId::getValue)
                                .orElse(null))
                .build();
    }
}
