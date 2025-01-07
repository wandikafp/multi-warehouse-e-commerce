package com.anw.user.service.domain;

import com.anw.user.service.domain.dto.user.UserAddressCommand;
import com.anw.user.service.domain.dto.user.UserAddressResponse;
import com.anw.user.service.domain.entity.UserAddress;
import com.anw.user.service.domain.mapper.UserAddressDataMapper;
import com.anw.user.service.domain.ports.output.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAddressCommandHandler {
    private final UserAddressRepository userAddressRepository;
    private final UserAddressDataMapper userAddressDataMapper;

    public List<UserAddressResponse> getUserAddresss(UUID userId) {
        return userAddressRepository.findByUserId(userId)
                .stream()
                .map(userAddressDataMapper::map)
                .collect(Collectors.toList());
    }

    public UserAddressResponse getUserAddressById(UUID id) {
        return userAddressDataMapper.map(userAddressRepository.findById(id));
    }

    @Transactional
    public UserAddressResponse createOrUpdateUserAddress(UserAddressCommand userAddressCommand) {
        UserAddress userAddress = userAddressDataMapper.map(userAddressCommand);
        if (userAddress.getId() == null) {
            userAddress.initializeUserAddress();
        }
        return userAddressDataMapper.map(userAddressRepository.save(userAddress));
    }

    @Transactional
    public void deleteUserAddress(UUID UserAddressId) {
        userAddressRepository.delete(UserAddressId);
    }
    
}
