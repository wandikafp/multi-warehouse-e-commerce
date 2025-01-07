package com.anw.user.service.domain;

import com.anw.user.service.domain.dto.user.UserAddressCommand;
import com.anw.user.service.domain.dto.user.UserAddressResponse;
import com.anw.user.service.domain.ports.input.service.UserAddressApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAddressApplicationServiceImpl implements UserAddressApplicationService {
    private final UserAddressCommandHandler userAddressCommandHandler;
    @Override
    public UserAddressResponse create(UserAddressCommand userAddressCommand) {
        return userAddressCommandHandler.createOrUpdateUserAddress(userAddressCommand);
    }

    @Override
    public UserAddressResponse update(UserAddressCommand userAddressCommand) {
        return userAddressCommandHandler.createOrUpdateUserAddress(userAddressCommand);
    }

    @Override
    public List<UserAddressResponse> getAll(UUID userId) {
        return userAddressCommandHandler.getUserAddresss(userId);
    }

    @Override
    public UserAddressResponse get(UUID id) {
        return userAddressCommandHandler.getUserAddressById(id);
    }

    @Override
    public void delete(UUID id) {
        userAddressCommandHandler.deleteUserAddress(id);
    }
}
