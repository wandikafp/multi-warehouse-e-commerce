package com.anw.user.service.domain;

import com.anw.user.service.domain.dto.user.UserRegisterCommand;
import com.anw.user.service.domain.dto.user.UserRegisterResponse;
import com.anw.user.service.domain.dto.user.UserUpdatePasswordCommand;
import com.anw.user.service.domain.entity.PasswordResetToken;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.entity.VerificationCode;
import com.anw.user.service.domain.event.UserRegisteredEvent;
import com.anw.user.service.domain.exception.UserDomainException;
import com.anw.user.service.domain.mapper.UserDataMapper;
import com.anw.user.service.domain.ports.input.jobs.SendResetPasswordEmailJob;
import com.anw.user.service.domain.ports.input.jobs.SendWelcomeEmailJob;
import com.anw.user.service.domain.ports.output.message.UserRegisteredMessagePublisher;
import com.anw.user.service.domain.ports.output.repository.PasswordResetTokenRepository;
import com.anw.user.service.domain.ports.output.repository.UserRepository;
import com.anw.user.service.domain.ports.output.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobrunr.scheduling.BackgroundJobRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCommandHandler {
    private final UserDataMapper userDataMapper;
    private final UserHelper userHelper;
    private final UserRegisteredMessagePublisher userRegisteredMessagePublisher;
    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    public UserRegisterResponse registerUser(UserRegisterCommand userRegisterCommand) {
        User user = userDataMapper.userRegisterCommandToUser(userRegisterCommand);
        UserRegisteredEvent userRegisteredEvent = userHelper.persistRegisterUser(user);
        log.info("user is registered with id: {}", userRegisteredEvent.getUser().getId().getValue());
        CompletableFuture.runAsync(() -> {
//            userRegisteredMessagePublisher.publish(userRegisteredEvent);
            sendVerificationEmail(user);
        });
        return userDataMapper.userToUserRegisterResponse(userRegisteredEvent.getUser());
    }

    private void sendVerificationEmail(User user) {
        VerificationCode verificationCode = new VerificationCode(user);
        user.setVerificationCode(verificationCode);
        verificationCodeRepository.save(verificationCode);
        SendWelcomeEmailJob sendWelcomEmailJob = new SendWelcomeEmailJob(user.getId().getValue());
        BackgroundJobRequest.enqueue(sendWelcomEmailJob);
    }

    @Transactional
    public void verifyEmail(String code) {
        VerificationCode verificationCode = Optional.ofNullable(verificationCodeRepository.findByCode(code))
                .orElseThrow(() -> new UserDomainException("invalid token"));
        User user = verificationCode.getUser();
        user.setVerified(true);
        userRepository.save(user);
        verificationCodeRepository.delete(verificationCode);
    }

    @Transactional
    public void forgotPassword(String email) {
        User user = Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new UserDomainException("user not found"));
        PasswordResetToken passwordResetToken = new PasswordResetToken(user);
        passwordResetTokenRepository.save(passwordResetToken);
        SendResetPasswordEmailJob sendResetPasswordEmailJob = new SendResetPasswordEmailJob(passwordResetToken.getId());
        BackgroundJobRequest.enqueue(sendResetPasswordEmailJob);
    }

    @Transactional
    public void resetPassword(UserUpdatePasswordCommand request) {
        PasswordResetToken passwordResetToken = Optional.ofNullable(
                passwordResetTokenRepository.findByToken(request.getPasswordResetToken()))
                .orElseThrow(() -> new UserDomainException("Password reset token not found"));

        if (passwordResetToken.isExpired()) {
            throw new UserDomainException("Password reset token is expired");
        }

        User user = passwordResetToken.getUser();
        user.updatePassword(request.getPassword());
        userRepository.save(user);
    }
}
