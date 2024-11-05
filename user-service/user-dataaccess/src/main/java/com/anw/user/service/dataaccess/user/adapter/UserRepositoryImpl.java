package com.anw.user.service.dataaccess.user.adapter;

import com.anw.user.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.anw.user.service.dataaccess.user.repository.UserJpaRepository;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.ports.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
}
