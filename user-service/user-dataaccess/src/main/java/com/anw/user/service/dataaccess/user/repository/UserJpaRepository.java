package com.anw.user.service.dataaccess.user.repository;

import com.anw.domain.valueobject.Role;
import com.anw.user.service.dataaccess.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository  extends JpaRepository<UserEntity, UUID> {
    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);
    @Query("SELECT u FROM UserEntity u WHERE u.role = :role")
    Optional<UserEntity> findByRole(@Param("role") Role role);
}
