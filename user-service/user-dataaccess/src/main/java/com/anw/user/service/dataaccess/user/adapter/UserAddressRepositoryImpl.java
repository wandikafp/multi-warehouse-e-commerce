package com.anw.user.service.dataaccess.user.adapter;

import com.anw.user.service.dataaccess.user.mapper.UserAddressDataAccessMapper;
import com.anw.user.service.dataaccess.user.repository.UserAddressJpaRepository;
import com.anw.user.service.domain.entity.UserAddress;
import com.anw.user.service.domain.ports.output.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserAddressRepositoryImpl implements UserAddressRepository {
    private final UserAddressJpaRepository userAddressJpaRepository;
    private final UserAddressDataAccessMapper userAddressDataAccessMapper;

    @Override
    public List<UserAddress> findByUserId(UUID userId) {
        return userAddressJpaRepository.findByUserId(userId)
                .stream()
                .map(userAddressDataAccessMapper::userAddressEntityToUserAddress)
                .collect(Collectors.toList());
    }

    @Override
    public UserAddress findById(UUID id) {
        return userAddressJpaRepository.findById(id)
                .map(userAddressDataAccessMapper::userAddressEntityToUserAddress)
                .orElse(null);
    }

    @Override
    public UserAddress save(UserAddress address) {
        return userAddressDataAccessMapper.userAddressEntityToUserAddress(
                userAddressJpaRepository.save(
                        userAddressDataAccessMapper.userAddressToUserAddressEntity(address)));
    }

    @Override
    public void delete(UUID id) {
        userAddressJpaRepository.deleteById(id);
    }
}
