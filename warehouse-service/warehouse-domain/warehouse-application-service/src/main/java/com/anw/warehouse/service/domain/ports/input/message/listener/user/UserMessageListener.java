package com.anw.warehouse.service.domain.ports.input.message.listener.user;

import com.anw.warehouse.service.domain.dto.message.UserResponse;

public interface UserMessageListener {
    void userRegistered(UserResponse userResponse);
}
