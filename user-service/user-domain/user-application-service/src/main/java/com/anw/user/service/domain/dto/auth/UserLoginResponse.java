package com.anw.user.service.domain.dto.auth;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class UserLoginResponse {
    private String token;
    private String refreshToken;
}
