package com.anw.user.service.domain.dto.user;

import com.anw.domain.valueobject.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UserRegisterResponse {
    private String id;
    private String email;
    private String fullName;
    private Role role;
}
