package com.anw.user.service.domain.entity;

import com.anw.domain.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.RandomStringUtils;

@EqualsAndHashCode(callSuper = true)
@Data
public class VerificationCode extends BaseEntity<Long> {
    private String code;
    private boolean emailSent = false;
    private User user;

    public VerificationCode(User user) {
        this.user = user;
        this.code = RandomStringUtils.random(6, false, true);
    }

    @Builder
    public VerificationCode(
            Long id,
            String code,
            boolean emailSent,
            User user
    ) {
        this.setId(id);
        this.code = code;
        this.emailSent = emailSent;
        this.user = user;
    }
}
