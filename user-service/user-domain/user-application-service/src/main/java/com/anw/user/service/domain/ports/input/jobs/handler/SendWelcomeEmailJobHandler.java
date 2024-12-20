package com.anw.user.service.domain.ports.input.jobs.handler;

import com.anw.user.service.domain.config.UserServiceConfig;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.entity.VerificationCode;
import com.anw.user.service.domain.ports.input.jobs.SendWelcomeEmailJob;
import com.anw.user.service.domain.ports.output.repository.UserRepository;
import com.anw.user.service.domain.ports.output.repository.VerificationCodeRepository;
import com.anw.user.service.domain.util.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class SendWelcomeEmailJobHandler implements JobRequestHandler<SendWelcomeEmailJob> {

    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final SpringTemplateEngine templateEngine;
    private final EmailService emailService;
    private final UserServiceConfig userServiceConfig;

    @Override
    @Transactional
    public void run(SendWelcomeEmailJob sendWelcomEmailJob) {
        User user = Optional.ofNullable(userRepository.findById(sendWelcomEmailJob.getUserId()))
                .orElseThrow(() -> new RuntimeException("User not found"));
        log.info("Sending welcome email to user with id: {}", sendWelcomEmailJob.getUserId());
        if (user.getVerificationCode() != null && !user.getVerificationCode().isEmailSent()) {
            sendWelcomeEmail(user, user.getVerificationCode());
        }
    }

    private void sendWelcomeEmail(User user, VerificationCode code) {
        String verificationLink = userServiceConfig.getBaseUrl() + "/api/users/verify-email?token=" + code.getCode();
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("user", user);
        thymeleafContext.setVariable("verificationLink", verificationLink);
        thymeleafContext.setVariable("applicationName", userServiceConfig.getApplicationName());
        String htmlBody = templateEngine.process("welcome-email", thymeleafContext);
        emailService.sendHtmlMessage(List.of(user.getEmail()), "Welcome to our platform", htmlBody);
        code.setEmailSent(true);
        verificationCodeRepository.save(code);
    }
}
