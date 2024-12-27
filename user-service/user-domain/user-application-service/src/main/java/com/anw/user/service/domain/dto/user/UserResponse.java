package com.anw.user.service.domain.dto.user;

import com.anw.domain.valueobject.Role;
import com.anw.user.service.domain.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserResponse {
    private String id;
    private Role role;
    private String fullName;
    private String email;
    private String profileImageUrl;
    private List<ConnectedAccountResponse> connectedAccounts = new ArrayList<>();
    private List<String> authorities = new ArrayList<>();

    public UserResponse(User user) {
        this.id = user.getId().getValue().toString();
        this.role = user.getRole();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.profileImageUrl = user.getProfileImageUrl();
        user.getConnectedAccounts().forEach((provider) -> {
            this.connectedAccounts.add(new ConnectedAccountResponse(provider.getProvider(), provider.getConnectedAt()));
        });
    }

    public UserResponse(User user, Collection<? extends GrantedAuthority> authorities) {
        this.id = user.getId().getValue().toString();
        this.role = user.getRole();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.profileImageUrl = user.getProfileImageUrl();
        user.getConnectedAccounts().forEach((provider) -> {
            this.connectedAccounts.add(new ConnectedAccountResponse(provider.getProvider(), provider.getConnectedAt()));
        });
        authorities.forEach(authority -> {
            this.authorities.add(authority.getAuthority());
        });
    }

    public record ConnectedAccountResponse(String provider, LocalDateTime connectedAt) {
    }
}
