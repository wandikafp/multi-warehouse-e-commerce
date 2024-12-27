package com.anw.user.service.domain;

import com.anw.domain.valueobject.Role;
import com.anw.user.service.domain.config.UserServiceConfig;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.entity.UserConnectedAccount;
import com.anw.user.service.domain.ports.output.repository.ConnectedAccountRepository;
import com.anw.user.service.domain.ports.output.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandlerImpl implements AuthenticationSuccessHandler {
    private final ConnectedAccountRepository connectedAccountRepository;
    private final UserRepository userRepository;
    @Setter
    private UserServiceConfig userServiceConfig;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
        String provider = authenticationToken.getAuthorizedClientRegistrationId();
        String providerId = authentication.getName();
        String email = authenticationToken.getPrincipal().getAttribute("email");

        UserConnectedAccount connectedAccount = connectedAccountRepository.findByProviderAndProviderId(provider, providerId);
        if (Objects.nonNull(connectedAccount)) {
            authenticateUser(connectedAccount.getUser(), response);
            return;
        }
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            UserConnectedAccount newConnectedAccount = UserConnectedAccount.builder()
                    .provider(provider)
                    .providerId(providerId)
                    .connectedAt(LocalDateTime.now())
                    .user(existingUser)
                    .build();
            existingUser.addConnectedAccount(newConnectedAccount);
            existingUser = userRepository.save(existingUser);
            connectedAccountRepository.save(newConnectedAccount);
            authenticateUser(existingUser, response);
        } else {
            User newUser = createUserFromOauth2User(authenticationToken);
            authenticateUser(newUser, response);
        }
        response.sendRedirect(userServiceConfig.getLoginSuccessUrl());
    }

    private void authenticateUser(User user, HttpServletResponse response) throws IOException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        response.sendRedirect(userServiceConfig.getLoginSuccessUrl());//TODO update this later to applicationProperties.getLoginSuccessUrl()
    }

    private User createUserFromOauth2User(OAuth2AuthenticationToken authentication) {
        OAuth2User oAuth2User = authentication.getPrincipal();
        User user = User.builder()
                .email(oAuth2User.getAttribute("email"))
                .fullName(oAuth2User.getAttribute("name"))
                .verified(true)
                .role(Role.CUSTOMER)
                .build();
        String provider = authentication.getAuthorizedClientRegistrationId();
        String providerId = authentication.getName();
        UserConnectedAccount connectedAccount = UserConnectedAccount.builder()
                .provider(provider)
                .providerId(providerId)
                .user(user)
                .build();
        user.addConnectedAccount(connectedAccount);
        user = userRepository.save(user);
        connectedAccountRepository.save(connectedAccount);
        return user;
    }
}
