package com.anw.user.service.domain;

import com.anw.domain.dto.PagedResponse;
import com.anw.domain.util.JwtUtil;
import com.anw.user.service.domain.dto.auth.UserLoginCommand;
import com.anw.user.service.domain.dto.auth.UserLoginResponse;
import com.anw.user.service.domain.dto.user.*;
import com.anw.user.service.domain.entity.PasswordResetToken;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.entity.VerificationCode;
import com.anw.user.service.domain.event.UserRegisteredEvent;
import com.anw.user.service.domain.exception.UserDomainException;
import com.anw.user.service.domain.mapper.UserDataMapper;
import com.anw.user.service.domain.ports.input.jobs.SendResetPasswordEmailJob;
import com.anw.user.service.domain.ports.input.jobs.SendWelcomeEmailJob;
import com.anw.user.service.domain.ports.output.message.UserRegisteredMessagePublisher;
import com.anw.user.service.domain.ports.output.repository.FileUploadRepository;
import com.anw.user.service.domain.ports.output.repository.PasswordResetTokenRepository;
import com.anw.user.service.domain.ports.output.repository.UserRepository;
import com.anw.user.service.domain.ports.output.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jobrunr.scheduling.BackgroundJobRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCommandHandler {
    private final UserDomainService userDomainService;
    private final UserDataMapper userDataMapper;
    private final UserHelper userHelper;
    private final UserRegisteredMessagePublisher userRegisteredMessagePublisher;
    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final FileUploadRepository fileUploadRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserLoginResponse processLogin(UserLoginCommand userLoginCommand) {
        User user = userRepository.findByEmail(userLoginCommand.getEmail());
        if (user == null) {
            throw new UserDomainException("User not found");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return UserLoginResponse.builder()
                .token(token)
                .build();
    }

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

    public UserResponse update(UserUpdateCommand userUpdateCommand) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userRepository.findById(user.getId().getValue());
        user.updateBasicDetails(userUpdateCommand.getFullName());
        user = userRepository.save(user);
        return new UserResponse(user);
    }

    public void sendEmailVerificationTest(UUID id) {
        SendWelcomeEmailJob sendWelcomeEmailJob = new SendWelcomeEmailJob(id);
        BackgroundJobRequest.enqueue(sendWelcomeEmailJob);
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

    @Transactional
    public UserResponse updatePassword(UserUpdatePasswordCommand userUpdatePasswordCommand) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getPassword() != null && !passwordEncoder.matches(userUpdatePasswordCommand.getOldPassword(), user.getPassword())) {
            throw new UserDomainException("Wrong password");
        }
        user.updatePassword(userUpdatePasswordCommand.getPassword());
        user = userRepository.save(user);
        return new UserResponse(user);
    }

    @Transactional
    public UserResponse uploadProfilePicture(MultipartFile file) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if(file.getOriginalFilename() == null) {
                throw new UserDomainException("Original file name is null");
            }
            String fileName = file.getOriginalFilename();
            byte[] fileData = FileUtils.readFileToByteArray(convertFile(file, fileName));
            if (!userDomainService.validateUpdateProfileImage(fileData, fileName)) {
                throw new UserDomainException("Invalid profile picture");
            }
            Path path = new File(fileName).toPath();
            String profilePictureUrl = fileUploadRepository.uploadFile(fileData, file.getOriginalFilename(), Files.probeContentType(path));
            user.setProfileImageUrl(profilePictureUrl);
            userRepository.save(user);
        } catch (Exception ex) {
            throw new UserDomainException(ex.getMessage());
        }
        return new UserResponse(user);
    }

    private File convertFile(MultipartFile file, String fileName) {
        try{
            File convertedFile = new File(fileName);
            FileOutputStream outputStream = new FileOutputStream(convertedFile);
            outputStream.write(file.getBytes());
            outputStream.close();
            log.debug("Converting multipart file : {}", convertedFile);
            return convertedFile;
        } catch (Exception e) {
            throw new UserDomainException("An error has occurred while converting the file");
        }
    }

    @Transactional
    public PagedResponse<UserBaseResponse> getUsers(int page, int size) {
        PagedResponse<User> users = userRepository.findAll(page, size);
        return new PagedResponse<>(users.getPage(), users.getSize(), users.getTotalElements(), users.getTotalPages(),
                users.getData()
                        .stream()
                        .map(userDataMapper::userToUserBaseResponse)
                        .collect(Collectors.toList()));

    }
}
