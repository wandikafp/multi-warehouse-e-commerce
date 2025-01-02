package com.anw.product.service.dataaccess.product.repository;

import com.anw.product.service.dataaccess.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {
    Page<ProductEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
