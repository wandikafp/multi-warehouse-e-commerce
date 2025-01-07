package com.anw.payment.service.dataaccess.payment.repository;

import com.anw.payment.service.dataaccess.payment.entity.PaymentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {
    Page<PaymentEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
