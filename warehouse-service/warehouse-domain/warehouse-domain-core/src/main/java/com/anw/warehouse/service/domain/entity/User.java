package com.anw.warehouse.service.domain.entity;

import com.anw.domain.entity.BaseEntity;
import com.anw.domain.valueobject.Address;
import com.anw.domain.valueobject.Role;
import com.anw.domain.valueobject.UserId;
import com.anw.domain.valueobject.WarehouseId;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class User extends BaseEntity<UserId> {
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final Role role;
    private final WarehouseId warehouseId;

    @Builder
    public User(UserId userId,
                     String userName,
                     String firstName,
                     String lastName,
                     Role role,
                WarehouseId warehouseId) {
        super.setId(userId);
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.warehouseId = warehouseId;
    }

}
