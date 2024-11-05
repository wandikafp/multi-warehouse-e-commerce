package com.anw.user.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserDomainService userDomainService() {
        return new UserDomainServiceImpl();
    }
}
