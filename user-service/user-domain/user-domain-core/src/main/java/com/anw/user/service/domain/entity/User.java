package com.anw.user.service.domain.entity;

import com.anw.domain.entity.AggregateRoot;
import com.anw.domain.valueobject.Role;
import com.anw.domain.valueobject.UserId;
import com.anw.domain.valueobject.WarehouseId;
import com.anw.user.service.domain.util.ApplicationContextProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Getter
public class User extends AggregateRoot<UserId> implements UserDetails {
    private final String email;
    @Setter
    private String password;
    private String fullName;
    @Setter
    private boolean verified;
    @Setter
    private String profileImageUrl;
    private final Role role;
    private final WarehouseId warehouseId;

    @Setter
    private VerificationCode verificationCode;
    private List<UserConnectedAccount> connectedAccounts = new ArrayList<>();


    @Builder
    public User(UserId userId,
                     String email,
                     String fullName,
                     String password,
                     String profileImageUrl,
                     boolean verified,
                     Role role,
                     WarehouseId warehouseId,
                     VerificationCode verificationCode) {
        super.setId(userId);
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.verified = verified;
        this.role = role;
        this.warehouseId = warehouseId;
        this.verificationCode = verificationCode;
    }

    public void initializeUser() {
        setId(new UserId(UUID.randomUUID()));
    }

//    public User(CreateUserRequest data) {
//        PasswordEncoder passwordEncoder = ApplicationContextProvider.bean(PasswordEncoder.class);
//        this.email = data.getEmail();
//        this.password = passwordEncoder.encode(data.getPassword());
//        this.firstName = data.getFirstName();
//        this.lastName = data.getLastName();
//        this.role = Role.USER;
//    }

    public void addConnectedAccount(UserConnectedAccount connectedAccount) {
        connectedAccounts.add(connectedAccount);
    }
//
//    public void update(UpdateUserRequest request) {
//        this.firstName = request.getFirstName();
//        this.lastName = request.getLastName();
//    }
//

    public void updateBasicDetails(String fullName) {
        this.fullName = fullName;
    }

    public void updatePassword(String newPassword) {
        PasswordEncoder passwordEncoder = ApplicationContextProvider.bean(PasswordEncoder.class);
        this.password = passwordEncoder.encode(newPassword);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
