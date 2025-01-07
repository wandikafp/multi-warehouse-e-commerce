package com.anw.user.service.domain.ports.input.service;

import com.anw.user.service.domain.dto.user.UserAddressCommand;
import com.anw.user.service.domain.dto.user.UserAddressResponse;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface UserAddressApplicationService {
    UserAddressResponse create(@Valid UserAddressCommand userAddressCommand);
    UserAddressResponse update(@Valid UserAddressCommand userAddressCommand);
    List<UserAddressResponse> getAll(UUID userId);
    UserAddressResponse get(UUID userId);
    void delete(UUID id);
}
