package com.anw.warehouse.service.domain.ports.output.repository;

import com.anw.warehouse.service.domain.entity.User;

public interface UserRepository {
    User save(User user);
}
