package com.example.productService.controller;

import com.example.productService.dto.request.CategoryCreateRequest;
import com.example.productService.dto.response.ApiResponse;
import com.example.productService.dto.response.CategoryResponse;
import com.example.productService.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryCreateRequest request){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(request))
                .build();
    }

    @GetMapping("/all")
    ApiResponse<List<CategoryResponse>> getCategory(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size ){
        Pageable pageable = PageRequest.of(page,size);
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getCategory(pageable))
                .build();
    }

    @GetMapping("/{categoryId}")
    ApiResponse<CategoryResponse> getCategoryById(@PathVariable String categoryId){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.getCategoryById(categoryId))
                .build();
    }

    @DeleteMapping("/{categoryId}")
    ApiResponse<String> deleteCategory(@PathVariable String categoryId){
        return ApiResponse.<String>builder()
                .message(categoryService.deleteCategory(categoryId))
                .build();
    }

}
