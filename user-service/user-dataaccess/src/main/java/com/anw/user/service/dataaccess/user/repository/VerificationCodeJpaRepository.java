package com.anw.user.service.dataaccess.user.repository;

import com.anw.user.service.dataaccess.user.entity.VerificationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VerificationCodeJpaRepository extends JpaRepository<VerificationCodeEntity, Long> {
    @Query("SELECT vc FROM VerificationCodeEntity vc WHERE vc.code = :code")
    Optional<VerificationCodeEntity> findByCode(String code);
}
