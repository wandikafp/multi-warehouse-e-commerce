package com.anw.user.service.domain.entity;

import com.anw.domain.entity.AggregateRoot;
import com.anw.domain.valueobject.Address;
import com.anw.domain.valueobject.Role;
import com.anw.domain.valueobject.UserId;
import com.anw.domain.valueobject.WarehouseId;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
public class User extends AggregateRoot<UserId> {
    private final String username;
    private final String fullName;
    private String password;
    private final Role role;
    private final WarehouseId warehouseId;

    public void setPassword(String password) {
        this.password = password;
    }

    @Builder
    public User(UserId userId,
                     String username,
                     String fullName,
                     String password,
                     Role role,
                     WarehouseId warehouseId) {
        super.setId(userId);
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
        this.warehouseId = warehouseId;
    }

    public void initializeUser() {
        setId(new UserId(UUID.randomUUID()));
    }
}
