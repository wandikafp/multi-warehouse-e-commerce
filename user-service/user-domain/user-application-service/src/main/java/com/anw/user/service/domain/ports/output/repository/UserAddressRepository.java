package com.anw.user.service.domain.ports.output.repository;

import com.anw.user.service.domain.entity.UserAddress;

import java.util.List;
import java.util.UUID;

public interface UserAddressRepository {
    List<UserAddress> findByUserId(UUID userId);
    UserAddress findById(UUID id);
    UserAddress save(UserAddress address);
    void delete(UUID id);
}
