package com.anw.user.service.domain;

import com.anw.domain.util.JwtUtil;
import com.anw.user.service.domain.dto.auth.UserLoginCommand;
import com.anw.user.service.domain.dto.auth.UserLoginResponse;
import com.anw.user.service.domain.dto.user.UserResponse;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.ports.input.service.UserAuthService;
import com.anw.user.service.domain.ports.output.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final AuthenticationManager authenticationManager;
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    private final UserCommandHandler userCommandHandler;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public UserLoginResponse login(UserLoginCommand body) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword()));
        return userCommandHandler.processLogin(body);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        this.logoutHandler.logout(request, response, authentication);
    }

    @Override
    public UserResponse getSession(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            String userEmail = jwtUtil.extractUserEmail(token);
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            User user = userRepository.findByEmail(userEmail);
            return new UserResponse(user, authorities);
        } else {
            throw new IllegalArgumentException("Invalid Authorization header");
        }
    }
}
