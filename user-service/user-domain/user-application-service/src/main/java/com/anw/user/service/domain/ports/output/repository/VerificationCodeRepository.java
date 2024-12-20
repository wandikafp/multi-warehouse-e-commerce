package com.anw.user.service.domain.ports.output.repository;

import com.anw.user.service.domain.entity.VerificationCode;

import java.util.Optional;

public interface VerificationCodeRepository {
    VerificationCode findByCode(String code);
    VerificationCode save(VerificationCode verificationCode);
    void delete(VerificationCode verificationCode);
}
