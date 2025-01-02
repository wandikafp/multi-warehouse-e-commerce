package com.anw.order.service.dataaccess.cart.repository;

import com.anw.order.service.dataaccess.cart.entity.CartEntity;
import com.anw.order.service.dataaccess.cart.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, UUID> {
    Optional<CartItemEntity> findByProductIdAndCustomerId(UUID productId, UUID customerId);
}
