package com.anw.user.service.dataaccess.user.mapper;

import com.anw.domain.valueobject.UserId;
import com.anw.domain.valueobject.WarehouseId;
import com.anw.user.service.dataaccess.user.entity.UserConnectedAccountEntity;
import com.anw.user.service.dataaccess.user.entity.UserEntity;
import com.anw.user.service.dataaccess.user.entity.VerificationCodeEntity;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.entity.VerificationCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificationCodeMapper {
    private final UserDataAccessMapper userDataAccessMapper;
    public VerificationCode verificationCodeEntityToVerificationCode(VerificationCodeEntity entity) {
        return VerificationCode.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .emailSent(entity.isEmailSent())
                .user(userDataAccessMapper.userEntityToUser(entity.getUser()))
                .build();
    }

    public VerificationCodeEntity verificationCodeToVerificationCodeEntity(VerificationCode verificationCode) {
        return new VerificationCodeEntity(
                verificationCode.getCode(),
                verificationCode.isEmailSent(),
                userDataAccessMapper.userToUserEntity(verificationCode.getUser()));
    }
}
