package com.anw.user.service.dataaccess.user.mapper;

import com.anw.domain.valueobject.BaseId;
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
                .email(userEntity.getEmail())
                .fullName(userEntity.getFullName())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .profileImageUrl(userEntity.getProfileImageUrl())
                .warehouseId(new WarehouseId(userEntity.getWarehouseId()))
                .build();
    }

    public UserEntity userToUserEntity(User user) {
        return UserEntity.builder()
                .id(user.getId().getValue())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .password(user.getPassword())
                .role(user.getRole())
                .profileImageUrl(user.getProfileImageUrl())
                .warehouseId(
                        Optional.ofNullable(user.getWarehouseId())
                                .map(BaseId::getValue)
                                .orElse(null))
                .build();
    }
}
