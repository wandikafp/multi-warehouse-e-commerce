package com.anw.user.service.dataaccess.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Getter
@NoArgsConstructor
public class VerificationCodeEntity extends AbstractEntity {
    private String code;
    @Setter
    private boolean emailSent = false;

    @OneToOne
    private UserEntity user;

    public VerificationCodeEntity(String code, boolean emailSent, UserEntity user) {
        this.user = user;
        this.emailSent = emailSent;
        this.code = code;
    }
}
