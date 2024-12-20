package com.anw.user.service.dataaccess.user.repository;

import com.anw.user.service.dataaccess.user.entity.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PasswordResetTokenJpaRepository extends JpaRepository<PasswordResetTokenEntity, Long> {
    @Query("SELECT prt FROM PasswordResetTokenEntity prt WHERE prt.token = ?1")
    Optional<PasswordResetTokenEntity> findByToken(String passwordResetToken);
}
