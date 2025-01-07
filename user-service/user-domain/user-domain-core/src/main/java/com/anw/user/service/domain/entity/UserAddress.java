package com.anw.user.service.domain.entity;

import com.anw.domain.entity.BaseEntity;
import com.anw.domain.valueobject.Address;
import com.anw.domain.valueobject.UserId;

import java.util.UUID;

public class UserAddress extends BaseEntity<UUID> {
    private final UserId userId;
    private final Address address;

    private UserAddress(Builder builder) {
        super.setId(builder.id);
        this.userId = builder.userId;
        this.address = builder.address;
    }

    public UserId getUserId() {
        return userId;
    }

    public Address getAddress() {
        return address;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private UserId userId;
        private Address address;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder userId(UserId userId) {
            this.userId = userId;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public UserAddress build() {
            return new UserAddress(this);
        }
    }

    public void initializeUserAddress() {
        setId(UUID.randomUUID());
    }
}
