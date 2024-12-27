package com.anw.user.service.domain.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateCommand {
    @NotBlank
    private String fullName;
}
