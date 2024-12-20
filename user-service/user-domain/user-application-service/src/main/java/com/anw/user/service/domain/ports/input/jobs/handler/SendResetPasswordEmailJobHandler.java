package com.anw.user.service.domain.ports.input.jobs.handler;

import com.anw.user.service.domain.config.UserServiceConfig;
import com.anw.user.service.domain.entity.PasswordResetToken;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.ports.input.jobs.SendResetPasswordEmailJob;
import com.anw.user.service.domain.ports.output.repository.PasswordResetTokenRepository;
import com.anw.user.service.domain.util.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SendResetPasswordEmailJobHandler implements JobRequestHandler<SendResetPasswordEmailJob> {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailService emailService;
    private final UserServiceConfig userServiceConfig;
    private final SpringTemplateEngine templateEngine;

    @Override
    @Transactional
    public void run(SendResetPasswordEmailJob sendResetPasswordEmailJob) throws Exception {
        PasswordResetToken resetToken = Optional.ofNullable(
                passwordResetTokenRepository.findById(sendResetPasswordEmailJob.getTokenId()))
                .orElseThrow(() -> new IllegalArgumentException("Token not found"));
        if (!resetToken.isEmailSent()) {
            sendResetPasswordEmail(resetToken.getUser(), resetToken);
        }
    }

    private void sendResetPasswordEmail(User user, PasswordResetToken token) {
        String link = userServiceConfig.getBaseUrl() + "/auth/reset-password?token=" + token.getToken();
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("user", user);
        thymeleafContext.setVariable("link", link);
        String htmlBody = templateEngine.process("password-reset", thymeleafContext);
        emailService.sendHtmlMessage(List.of(user.getEmail()), "Password reset requested", htmlBody);
        token.onEmailSent();
        passwordResetTokenRepository.save(token);
    }

}
