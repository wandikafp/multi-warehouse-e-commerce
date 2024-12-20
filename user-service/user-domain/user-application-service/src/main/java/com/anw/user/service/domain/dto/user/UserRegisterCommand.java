package com.anw.user.service.domain.dto.user;

import com.anw.domain.valueobject.WarehouseId;
import com.anw.user.service.domain.util.validator.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@PasswordMatch(passwordField = "password", passwordConfirmationField = "passwordConfirmation")
@Builder
public class UserRegisterCommand {
    @Email
    private String email;

    @NotNull
    @Length(min = 8)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "must contain at least one uppercase letter, one lowercase letter, and one digit.")
    private String password;
    private String passwordConfirmation;
    @NotNull
    private String fullName;
    private String role;
    private WarehouseId warehouseId;
}
