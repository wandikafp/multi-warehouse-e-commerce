package com.anw.user.service.dataaccess.user.adapter;

import com.anw.domain.dto.PagedResponse;
import com.anw.domain.valueobject.Role;
import com.anw.user.service.dataaccess.user.entity.UserEntity;
import com.anw.user.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.anw.user.service.dataaccess.user.repository.UserJpaRepository;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.exception.UserDomainException;
import com.anw.user.service.domain.ports.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public User findByRole(Role role) {
        Optional<UserEntity> userEntity = userJpaRepository.findByRole(role);
        return userEntity.map(userDataAccessMapper::userEntityToUser).orElse(null);
    }

    @Override
    public User findById(UUID id) {
        Optional<UserEntity> userEntity = userJpaRepository.findById(id);
        if (userEntity.isEmpty()) {
            throw new UserDomainException("id not found");
        }
        return userDataAccessMapper.userEntityToUser(userEntity.get());
    }

    @Override
    public PagedResponse<User> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<UserEntity> users = userJpaRepository.findAll(pageable);
        return new PagedResponse<>(users.map(userDataAccessMapper::userEntityToUser));
    }
}
