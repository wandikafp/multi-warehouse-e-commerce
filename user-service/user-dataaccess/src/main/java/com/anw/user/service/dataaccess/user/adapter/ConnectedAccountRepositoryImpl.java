package com.anw.user.service.dataaccess.user.adapter;

import com.anw.user.service.dataaccess.user.entity.UserConnectedAccountEntity;
import com.anw.user.service.dataaccess.user.mapper.ConnectedAccountMapper;
import com.anw.user.service.dataaccess.user.repository.ConnectedAccountJpaRepository;
import com.anw.user.service.domain.entity.UserConnectedAccount;
import com.anw.user.service.domain.ports.output.repository.ConnectedAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConnectedAccountRepositoryImpl implements ConnectedAccountRepository {
    private final ConnectedAccountJpaRepository connectedAccountJpaRepository;
    private final ConnectedAccountMapper connectedAccountMapper;

    @Override
    public UserConnectedAccount findByProviderAndProviderId(String provider, String providerId) {
        Optional<UserConnectedAccountEntity> userConnectedAccount =
                connectedAccountJpaRepository.findByProviderAndProviderId(provider, providerId);
        return userConnectedAccount.map(connectedAccountMapper::userConnectedAccountEntityToUserConnectedAccount).orElse(null);
    }

    @Override
    public UserConnectedAccount save(UserConnectedAccount userConnectedAccount) {
        return connectedAccountMapper.userConnectedAccountEntityToUserConnectedAccount(
                connectedAccountJpaRepository.save(connectedAccountMapper.userConnectedAccountToUserConnectedAccountEntity(userConnectedAccount)));
    }
}
