package com.example.productService.controller;

import com.example.productService.dto.request.ProductRequest;
import com.example.productService.dto.request.ProductUpdateRequest;
import com.example.productService.dto.response.ApiResponse;
import com.example.productService.dto.response.ProductResponse;
import com.example.productService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    ApiResponse<ProductResponse> createProduct(@RequestBody ProductRequest request){
        return ApiResponse.<ProductResponse>builder()
                .result(productService.createProduct(request))
                .build();
    }

    @GetMapping("/{productId}")
    ApiResponse<ProductResponse> getProduct(@PathVariable String productId){
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProduct(productId))
                .build();
    }

    @GetMapping("/all")
    ApiResponse<List<ProductResponse>> getAllProduct(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page,size);
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProduct(pageable))
                .build();
    }

    @PutMapping("/{productId}")
    ApiResponse<ProductResponse> updateProduct(@PathVariable String productId, @RequestBody ProductUpdateRequest request){
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProduct(productId,request))
                .build();
    }

    @DeleteMapping("/{productId}")
    ApiResponse<String> deleteProduct(@PathVariable String productId){
        return ApiResponse.<String>builder()
                .message(productService.deleteProduct(productId))
                .build();
    }
}
