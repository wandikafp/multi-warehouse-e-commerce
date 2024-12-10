package com.anw.user.service.domain.dto.register;

import com.anw.domain.valueobject.WarehouseId;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRegisterCommand {
    @NotNull
    private String userName;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String password;
    private String role;
    private WarehouseId warehouseId;
}
