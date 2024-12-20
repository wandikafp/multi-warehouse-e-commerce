package com.anw.user.service.dataaccess.user.mapper;

import com.anw.user.service.dataaccess.user.entity.UserConnectedAccountEntity;
import com.anw.user.service.domain.entity.UserConnectedAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConnectedAccountMapper {
    private final UserDataAccessMapper userMapper;
    public UserConnectedAccount userConnectedAccountEntityToUserConnectedAccount(UserConnectedAccountEntity entity) {
        return UserConnectedAccount.builder()
                .provider(entity.getProvider())
                .providerId(entity.getProviderId())
                .connectedAt(entity.getConnectedAt())
                .user(userMapper.userEntityToUser(entity.getUser()))
                .build();
    }

    public UserConnectedAccountEntity userConnectedAccountToUserConnectedAccountEntity(UserConnectedAccount userConnectedAccount) {
        return new UserConnectedAccountEntity(userConnectedAccount.getProvider(),
                userConnectedAccount.getProviderId(),
                userMapper.userToUserEntity(userConnectedAccount.getUser()));
    }
}
