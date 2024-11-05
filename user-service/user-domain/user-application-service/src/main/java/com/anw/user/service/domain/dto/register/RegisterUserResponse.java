package com.anw.user.service.domain.dto.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RegisterUserResponse {
    private String userName;
    private String firstName;
    private String lastName;


}
