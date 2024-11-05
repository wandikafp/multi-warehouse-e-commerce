package com.anw.warehouse.service.domain;

import com.anw.warehouse.service.domain.dto.message.UserResponse;
import com.anw.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.anw.warehouse.service.domain.ports.input.message.listener.user.UserMessageListener;
import com.anw.warehouse.service.domain.ports.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserMessageListenerImpl implements UserMessageListener {
    private final UserRepository userRepository;
    private final WarehouseDataMapper warehouseDataMapper;
    @Override
    public void userRegistered(UserResponse userResponse) {
        userRepository.save(warehouseDataMapper.userResponseToUser(userResponse));
    }
}
