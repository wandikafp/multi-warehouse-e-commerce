package com.anw.user.service.domain.util;

import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.exception.UserDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Slf4j
public class SecurityUtil {
    private static final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    public static User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User user) {
            return user;
        } else {
            log.error("User requested but not found in SecurityContextHolder");
//            throw ApiException.builder().status(401).message("Authentication required").build();
            throw new UserDomainException("Authentication required");
        }
    }
}
