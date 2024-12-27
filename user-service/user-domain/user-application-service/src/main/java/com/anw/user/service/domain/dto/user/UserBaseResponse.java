package com.anw.user.service.domain.dto.user;

import com.anw.domain.valueobject.Role;
import com.anw.user.service.domain.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
public class UserBaseResponse {
    private String id;
    private Role role;
    private String fullName;
    private String email;
}
