package com.anw.product.service.dataaccess.product.adapter;

import com.anw.product.service.dataaccess.product.mapper.CategoryDataAccessMapper;
import com.anw.product.service.dataaccess.product.repository.CategoryJpaRepository;
import com.anw.product.service.domain.entity.Category;
import com.anw.product.service.domain.ports.output.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryJpaRepository categoryJpaRepository;
    private final CategoryDataAccessMapper categoryDataAccessMapper;

    @Override
    public Optional<Category> findById(UUID categoryId) {
        return categoryJpaRepository.findById(categoryId)
                .map(categoryDataAccessMapper::categoryEntityToCategory);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryJpaRepository.findByName(name)
                .map(categoryDataAccessMapper::categoryEntityToCategory);
    }

    @Override
    public Category save(Category category) {
        return categoryDataAccessMapper.categoryEntityToCategory(
                categoryJpaRepository.save(categoryDataAccessMapper.categoryToCategoryEntity(category)));
    }

    @Override
    public void deleteById(UUID categoryId) {
        categoryJpaRepository.deleteById(categoryId);
    }

    @Override
    public List<Category> findAll() {
        return categoryJpaRepository.findAll()
                .stream()
                .map(categoryDataAccessMapper::categoryEntityToCategory)
                .collect(Collectors.toList());
    }
}
