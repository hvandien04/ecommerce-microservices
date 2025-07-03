package com.example.productService.service;

import com.example.productService.dto.request.ProductRequest;
import com.example.productService.dto.response.ProductResponse;
import com.example.productService.entity.Category;
import com.example.productService.entity.Product;
import com.example.productService.exception.AppException;
import com.example.productService.exception.ErrorCode;
import com.example.productService.mapper.ProductMapper;
import com.example.productService.repository.CategoryRepository;
import com.example.productService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final IdGeneratorService idGeneratorService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductResponse createProduct(ProductRequest request){
        Product product = productMapper.toProduct(request);
        product.setProductsId(idGeneratorService.generateRandomId("PRD",productRepository::existsById));
        return productMapper.toProductResponse(productRepository.save(product));
    }

    public ProductResponse getProduct(String productId){
        return productRepository.findById(productId).map(productMapper::toProductResponse).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    public List<ProductResponse> getAllProduct(Pageable pageable){
        return productRepository.findAll(pageable).stream().map(productMapper::toProductResponse).toList();
    }

    public ProductResponse updateProduct(String productId, ProductRequest request){
        Product product = productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productMapper.updateProduct(product,request);
        Category category = categoryRepository.findById(request.getCategoriesId()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        product.setCategory(category);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    public String deleteProduct(String productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return productId + " deleted";
    }
}
