package com.anw.user.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "user-service")
public class UserServiceConfig {
    private String RegisterUserTopicName;
    private List<String> allowedOrigins;
    private String applicationName;
    private String baseUrl;
    private String loginPageUrl;
    private String loginSuccessUrl;
    private String adminUserEmail;
    private String adminUserPassword;
    private String jwtSecret;
    private String jwtIssuer;
}
