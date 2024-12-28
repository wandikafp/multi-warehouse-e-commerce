package com.anw.product.service.dataaccess.product.adapter;

import com.anw.domain.dto.PagedRequest;
import com.anw.domain.dto.PagedResponse;
import com.anw.product.service.dataaccess.product.entity.ProductEntity;
import com.anw.product.service.dataaccess.product.mapper.ProductDataAccessMapper;
import com.anw.product.service.dataaccess.product.repository.ProductJpaRepository;
import com.anw.product.service.domain.entity.Product;
import com.anw.product.service.domain.exception.ProductDomainException;
import com.anw.product.service.domain.ports.output.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;
    private final ProductDataAccessMapper productDataAccessMapper;

    @Override
    public PagedResponse<Product> findAll(PagedRequest pagedRequest) {
        Pageable pageable = PageRequest.of(pagedRequest.getPage(), pagedRequest.getSize());
        Page<ProductEntity> productEntities;
        if (StringUtils.hasText(pagedRequest.getQuery())) {
            productEntities = productJpaRepository.findByNameContainingIgnoreCase(pagedRequest.getQuery(), pageable);
        } else {
            productEntities = productJpaRepository.findAll(pageable);
        }
        return new PagedResponse<>(productEntities.map(productDataAccessMapper::productEntityToProduct));
    }

    @Override
    public Product save(Product product) {
        return productDataAccessMapper.productEntityToProduct(
                productJpaRepository.save(productDataAccessMapper.productToProductEntity(product)));
    }

    @Override
    public Product getById(Product product) {
        try {
            return productDataAccessMapper.productEntityToProduct(
                    productJpaRepository.getReferenceById(product.getId().getValue()));
        } catch (EntityNotFoundException ex) {
            log.error("product with id {} is not found", product.getId().getValue());
            throw new ProductDomainException("product with id " + product.getId().getValue() + " is not found");
        }
    }

    @Override
    public List<Product> search(String query) {
        return productJpaRepository.findByNameContainingIgnoreCase(query)
                .stream()
                .map(productDataAccessMapper::productEntityToProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(String productId) {
        return productJpaRepository.findById(UUID.fromString(productId))
                .map(productDataAccessMapper::productEntityToProduct);
    }

    @Override
    public void deleteById(UUID productId) {
        productJpaRepository.deleteById(productId);
    }
}
