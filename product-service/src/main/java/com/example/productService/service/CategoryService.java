package com.example.productService.service;

import com.example.productService.dto.request.CategoryCreateRequest;
import com.example.productService.dto.response.CategoryResponse;
import com.example.productService.entity.Category;
import com.example.productService.exception.AppException;
import com.example.productService.exception.ErrorCode;
import com.example.productService.mapper.CategoryMapper;
import com.example.productService.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final IdGeneratorService idGeneratorService;

    public CategoryResponse createCategory(CategoryCreateRequest request){
        Category category = categoryMapper.toCategory(request);
        category.setCategoriesId(idGeneratorService.generateRandomId("CAT",categoryRepository::existsById));
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public List<CategoryResponse> getCategory(Pageable pageable){
        return categoryRepository.findAll(pageable).stream().map(categoryMapper::toCategoryResponse).toList();
    }

    public CategoryResponse getCategoryById(String categoryId){
        return categoryRepository.findById(categoryId).map(categoryMapper::toCategoryResponse).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    public String deleteCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        categoryRepository.deleteById(categoryId);
        return categoryId + " deleted";
    }
}
