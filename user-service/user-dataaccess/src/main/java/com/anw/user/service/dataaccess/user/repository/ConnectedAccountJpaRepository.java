package com.anw.user.service.dataaccess.user.repository;

import com.anw.user.service.dataaccess.user.entity.UserConnectedAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConnectedAccountJpaRepository extends JpaRepository<UserConnectedAccountEntity, Long> {
  @Query("SELECT a FROM UserConnectedAccountEntity a WHERE a.provider = :provider AND a.providerId = :providerId")
  Optional<UserConnectedAccountEntity> findByProviderAndProviderId(@Param("provider") String provider, @Param("providerId") String providerId);

}
