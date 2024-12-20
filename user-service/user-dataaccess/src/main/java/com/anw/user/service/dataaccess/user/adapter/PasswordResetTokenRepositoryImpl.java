package com.anw.user.service.dataaccess.user.adapter;

import com.anw.user.service.dataaccess.user.entity.PasswordResetTokenEntity;
import com.anw.user.service.dataaccess.user.mapper.PasswordResetTokenMapper;
import com.anw.user.service.dataaccess.user.repository.PasswordResetTokenJpaRepository;
import com.anw.user.service.domain.entity.PasswordResetToken;
import com.anw.user.service.domain.ports.output.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class PasswordResetTokenRepositoryImpl implements PasswordResetTokenRepository {
    private final PasswordResetTokenJpaRepository passwordResetTokenJpaRepository;
    private final PasswordResetTokenMapper passwordResetTokenMapper;
    @Override
    public PasswordResetToken save(PasswordResetToken passwordResetToken) {
        return passwordResetTokenMapper.passwordResetTokenEntityToPasswordResetToken(
                passwordResetTokenJpaRepository.save(passwordResetTokenMapper.passwordResetTokenToPasswordResetTokenEntity(passwordResetToken)));

    }

    @Override
    public PasswordResetToken findById(Long id) {
        Optional<PasswordResetTokenEntity> entity = passwordResetTokenJpaRepository.findById(id);
        return entity.map(passwordResetTokenMapper::passwordResetTokenEntityToPasswordResetToken).orElse(null);
    }

    @Override
    public PasswordResetToken findByToken(String passwordResetToken) {
        Optional<PasswordResetTokenEntity> entity = passwordResetTokenJpaRepository.findByToken(passwordResetToken);
        return entity.map(passwordResetTokenMapper::passwordResetTokenEntityToPasswordResetToken).orElse(null);
    }
}
