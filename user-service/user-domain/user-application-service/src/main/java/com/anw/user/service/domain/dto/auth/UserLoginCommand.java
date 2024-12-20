package com.anw.user.service.domain.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserLoginCommand {
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
}
