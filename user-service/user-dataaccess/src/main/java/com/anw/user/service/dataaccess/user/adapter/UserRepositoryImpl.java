package com.anw.user.service.dataaccess.user.adapter;

import com.anw.user.service.dataaccess.user.entity.UserEntity;
import com.anw.user.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.anw.user.service.dataaccess.user.repository.UserJpaRepository;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.exception.UserDomainException;
import com.anw.user.service.domain.ports.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserDataAccessMapper userDataAccessMapper;
    private final UserJpaRepository userJpaRepository;
    @Override
    public User save(User user) {
        return userDataAccessMapper.userEntityToUser(
                userJpaRepository.save(userDataAccessMapper.userToUserEntity(user)));
    }

    @Override
    public User findByEmail(String email) {
        Optional<UserEntity> userEntity = userJpaRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            throw new UserDomainException("email not found");
        }
        return userDataAccessMapper.userEntityToUser(userEntity.get());
    }

    @Override
    public User findById(UUID id) {
        Optional<UserEntity> userEntity = userJpaRepository.findById(id);
        if (userEntity.isEmpty()) {
            throw new UserDomainException("id not found");
        }
        return userDataAccessMapper.userEntityToUser(userEntity.get());
    }
}
