package com.anw.user.service.domain.dto.login;

import com.anw.domain.valueobject.WarehouseId;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserLoginCommand {
    @NotNull
    private String userName;
    @NotNull
    private String password;
}
