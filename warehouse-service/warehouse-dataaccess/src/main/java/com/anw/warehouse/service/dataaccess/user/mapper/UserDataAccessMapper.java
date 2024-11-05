package com.anw.warehouse.service.dataaccess.user.mapper;

import com.anw.domain.valueobject.BaseId;
import com.anw.domain.valueobject.Role;
import com.anw.domain.valueobject.UserId;
import com.anw.domain.valueobject.WarehouseId;
import com.anw.warehouse.service.dataaccess.user.entity.UserEntity;
import com.anw.warehouse.service.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class UserDataAccessMapper {
    public User userEntityToUser(UserEntity userEntity) {
        return User.builder()
                .userId(new UserId(userEntity.getId()))
                .userName(userEntity.getUserName())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .role(Role.valueOf(userEntity.getRole()))
                .warehouseId(Objects.nonNull(userEntity.getWarehouseId()) ?
                        new WarehouseId(userEntity.getWarehouseId()) :
                        null)
                .build();
    }

    public UserEntity userToUserEntity(User user) {
        return UserEntity.builder()
                .id(user.getId().getValue())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().toString())
                .warehouseId(Optional.ofNullable(user.getWarehouseId())
                        .map(BaseId::getValue)
                        .orElse(null))
                .build();
    }
}
