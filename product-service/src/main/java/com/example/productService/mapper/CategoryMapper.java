package com.example.productService.mapper;

import com.example.productService.dto.request.CategoryCreateRequest;
import com.example.productService.dto.response.CategoryResponse;
import com.example.productService.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreateRequest request);
    CategoryResponse toCategoryResponse(Category category);
}
