package com.anw.user.service.dataaccess.user.repository;

import com.anw.domain.valueobject.Role;
import com.anw.user.service.dataaccess.user.entity.UserAddressEntity;
import com.anw.user.service.dataaccess.user.entity.UserEntity;
import com.anw.user.service.domain.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAddressJpaRepository extends JpaRepository<UserAddressEntity, UUID> {
    List<UserAddressEntity> findByUserId(UUID id);
}
