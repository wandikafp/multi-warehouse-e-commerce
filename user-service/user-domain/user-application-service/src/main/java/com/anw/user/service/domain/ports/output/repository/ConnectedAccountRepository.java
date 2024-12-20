package com.anw.user.service.domain.ports.output.repository;

import com.anw.user.service.domain.entity.UserConnectedAccount;

public interface ConnectedAccountRepository {
    UserConnectedAccount findByProviderAndProviderId(String provider, String providerId);
    UserConnectedAccount save(UserConnectedAccount userConnectedAccount);
}
