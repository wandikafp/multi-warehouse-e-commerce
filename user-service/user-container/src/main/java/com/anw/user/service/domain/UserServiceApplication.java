package com.anw.user.service.domain;

import com.anw.domain.valueobject.Role;
import com.anw.domain.valueobject.UserId;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.ports.output.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@EnableJpaRepositories(basePackages = { "com.anw.user.service.dataaccess" })
@EntityScan(basePackages = { "com.anw.user.service.dataaccess" })
@SpringBootApplication(scanBasePackages = "com.anw")
public class UserServiceApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("User Service Application is running");
        User admiUser = userRepository.findByRole(Role.SUPER_ADMIN);
        if (admiUser == null) {
            User adminUser = User.builder()
                    .userId(new UserId(UUID.randomUUID()))
                    .email("admin@mail.com")
                    .fullName("Super Admin")
                    .password(new BCryptPasswordEncoder().encode("admin"))
                    .role(Role.SUPER_ADMIN)
                    .build();
            userRepository.save(adminUser);
            User test = User.builder()
                    .userId(new UserId(UUID.randomUUID()))
                    .email("test1@mail.com")
                    .fullName("Test User")
                    .password(new BCryptPasswordEncoder().encode("test"))
                    .role(Role.CUSTOMER)
                    .build();
            userRepository.save(test);
        }
    }
}
