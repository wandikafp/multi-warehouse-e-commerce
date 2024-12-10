package com.anw.user.service.domain;

import com.anw.domain.util.JwtUtil;
import com.anw.user.service.domain.dto.login.UserLoginResponse;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.event.UserRegisteredEvent;
import com.anw.user.service.domain.exception.UserDomainException;
import com.anw.user.service.domain.ports.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserHelper {
    private final UserDomainService userDomainService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    @Transactional
    public UserRegisteredEvent persistRegisterUser(User User) {
        UserRegisteredEvent UserCreatedEvent = userDomainService.registerUser(User, null);
        saveUser(User);
        log.info("initialize User with id: {}", User.getId().getValue());
        return UserCreatedEvent;
    }

    public UserLoginResponse processingLogin(User user) {
        User saveUser = userRepository.getByUsername(user);
        if (!userDomainService.isValidLogin(user, saveUser)) {
            throw new UserDomainException("Invalid username or password");
        }
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());
        extraClaims.put("warehouseId", user.getWarehouseId());
        return UserLoginResponse.builder()
                .token(jwtUtil.generateToken(extraClaims, user.getUsername()))
                .expiresIn(jwtUtil.getExpirationTime())
                .build();

    }

    private void saveUser(User user) {
        User userResult = userRepository.save(user);
        log.info("User is saved with id: {}", userResult.getId().getValue());
    }
}
