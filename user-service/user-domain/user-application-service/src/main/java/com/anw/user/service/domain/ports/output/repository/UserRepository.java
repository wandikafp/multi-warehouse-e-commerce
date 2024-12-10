package com.anw.user.service.domain.ports.output.repository;

import com.anw.user.service.domain.entity.User;

public interface UserRepository {
    User save(User user);
    User getByUsername(User user);
}
