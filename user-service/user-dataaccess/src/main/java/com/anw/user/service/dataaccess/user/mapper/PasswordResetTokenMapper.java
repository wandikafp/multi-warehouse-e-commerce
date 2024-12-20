package com.anw.user.service.dataaccess.user.mapper;

import com.anw.user.service.dataaccess.user.entity.PasswordResetTokenEntity;
import com.anw.user.service.domain.entity.PasswordResetToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordResetTokenMapper {
    private final UserDataAccessMapper userMapper;
    public PasswordResetToken passwordResetTokenEntityToPasswordResetToken(PasswordResetTokenEntity entity) {
        return PasswordResetToken.builder()
                .id(entity.getId())
                .token(entity.getToken())
                .emailSent(entity.isEmailSent())
                .expiresAt(entity.getExpiresAt())
                .user(userMapper.userEntityToUser(entity.getUser()))
                .build();
    }

    public PasswordResetTokenEntity passwordResetTokenToPasswordResetTokenEntity(PasswordResetToken passwordResetToken) {
        return new PasswordResetTokenEntity(
                passwordResetToken.getToken(),
                passwordResetToken.isEmailSent(),
                passwordResetToken.getExpiresAt(),
                userMapper.userToUserEntity(passwordResetToken.getUser()));
    }
}
