package com.anw.order.service.dataaccess.cart.repository;

import com.anw.order.service.dataaccess.cart.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity, UUID> {
    Optional<CartEntity> findByCustomerId(UUID customerId);
}
