package com.anw.user.service.dataaccess.user.adapter;

import com.anw.user.service.dataaccess.user.entity.UserConnectedAccountEntity;
import com.anw.user.service.dataaccess.user.entity.VerificationCodeEntity;
import com.anw.user.service.dataaccess.user.mapper.VerificationCodeMapper;
import com.anw.user.service.dataaccess.user.repository.VerificationCodeJpaRepository;
import com.anw.user.service.domain.entity.VerificationCode;
import com.anw.user.service.domain.ports.output.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class VerificationCodeRepositoryImpl implements VerificationCodeRepository {
    private final VerificationCodeJpaRepository verificationCodeJpaRepository;
    private final VerificationCodeMapper verificationCodeMapper;
    @Override
    public VerificationCode findByCode(String code) {
        Optional<VerificationCodeEntity> entity =
                verificationCodeJpaRepository.findByCode(code);
        return entity.map(verificationCodeMapper::verificationCodeEntityToVerificationCode).orElse(null);
    }

    @Override
    public VerificationCode save(VerificationCode verificationCode) {
        return verificationCodeMapper.verificationCodeEntityToVerificationCode(
                verificationCodeJpaRepository.save(
                verificationCodeMapper.verificationCodeToVerificationCodeEntity(verificationCode)));
    }

    @Override
    public void delete(VerificationCode verificationCode) {
        verificationCodeJpaRepository.delete(
                verificationCodeMapper.verificationCodeToVerificationCodeEntity(verificationCode));
    }
}
