package com.anw.product.service.dataaccess.product.mapper;

import com.anw.product.service.dataaccess.product.entity.CategoryEntity;
import com.anw.product.service.domain.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDataAccessMapper {

    public CategoryEntity categoryToCategoryEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public Category categoryEntityToCategory(CategoryEntity categoryEntity) {
        return Category.builder()
                .categoryId(categoryEntity.getId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .build();
    }
}
