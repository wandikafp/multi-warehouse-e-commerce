package com.anw.user.service.domain.ports.output.repository;

import com.anw.domain.dto.PagedResponse;
import com.anw.domain.valueobject.Role;
import com.anw.user.service.domain.entity.User;

import java.util.UUID;

public interface UserRepository {
    User save(User user);
    User findByEmail(String email);
    User findByRole(Role role);
    User findById(UUID id);
    PagedResponse<User> findAll(int page, int size);
}
