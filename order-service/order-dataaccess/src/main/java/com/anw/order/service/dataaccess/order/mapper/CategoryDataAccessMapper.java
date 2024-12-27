package com.anw.order.service.dataaccess.order.mapper;

import com.anw.order.service.domain.entity.Category;
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
