package com.anw.user.service.domain.ports.output.repository;

import com.anw.user.service.domain.entity.PasswordResetToken;
import com.anw.user.service.domain.entity.User;

public interface PasswordResetTokenRepository {
    PasswordResetToken save(PasswordResetToken passwordResetToken);
    PasswordResetToken findById(Long id);
    PasswordResetToken findByToken(String passwordResetToken);
}
