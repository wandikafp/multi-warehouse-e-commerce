package com.anw.user.service.dataaccess.user.repository;

import com.anw.user.service.dataaccess.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserJpaRepository  extends JpaRepository<UserEntity, UUID> {
}
