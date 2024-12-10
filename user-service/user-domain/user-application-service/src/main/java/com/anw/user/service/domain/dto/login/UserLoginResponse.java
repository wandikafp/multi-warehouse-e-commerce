package com.anw.user.service.domain.dto.login;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserLoginResponse {
    private String token;
    private Long expiresIn;
}
