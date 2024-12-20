package com.anw.user.service.domain.dto.user;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserForgotPasswordCommand {
    @Email
    private String email;
}
