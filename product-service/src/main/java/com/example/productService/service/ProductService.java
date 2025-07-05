package com.example.productService.service;

import com.example.productService.dto.request.ProductRequest;
import com.example.productService.dto.request.ProductUpdateRequest;
import com.example.productService.dto.response.ProductResponse;
import com.example.productService.entity.Category;
import com.example.productService.entity.Product;
import com.example.productService.entity.ProductAttribute;
import com.example.productService.entity.ProductImage;
import com.example.productService.exception.AppException;
import com.example.productService.exception.ErrorCode;
import com.example.productService.mapper.ProductMapper;
import com.example.productService.repository.CategoryRepository;
import com.example.productService.repository.ProductAttributeRepository;
import com.example.productService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final IdGeneratorService idGeneratorService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final ProductAttributeRepository productAttributeRepository;

    @Transactional
    public ProductResponse createProduct(ProductRequest request){
        Product product = productMapper.toProduct(request);
        product.setProductsId(idGeneratorService.generateRandomId("PRD",productRepository::existsById));
        if(!request.getAttributes().isEmpty()) {
            List<ProductAttribute> productAttributes = request.getAttributes().stream().map(attrReq -> ProductAttribute.builder ()
                    .productAttributesId(idGeneratorService.generateRandomId("ATT",productAttributeRepository::existsById))
                    .key(attrReq.getKey())
                    .value(attrReq.getValue())
                    .products(product)
                    .build()).toList();
            product.setProductAttributes(productAttributes);
        }

        if(!request.getImages().isEmpty()) {
            List<ProductImage> productImages = request.getImages().stream().map(req -> ProductImage.builder()
                    .id(idGeneratorService.generateRandomId("IMG",productRepository::existsById))
                    .products(product)
                    .imageUrl(req.getImageUrl())
                    .build()).toList();
            product.setProductImages(productImages);
        }

        Category category = categoryRepository.findById(request.getCategoriesId()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        product.setCategory(category);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    public ProductResponse getProduct(String productId){
        return productRepository.findById(productId).map(productMapper::toProductResponse).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    public List<ProductResponse> getAllProduct(Pageable pageable){
        return productRepository.findAll(pageable).stream().map(productMapper::toProductResponse).toList();
    }

    public ProductResponse updateProduct(String productId, ProductUpdateRequest request){
        Product product = productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productMapper.updateProduct(product,request);
        Category category = categoryRepository.findById(request.getCategoriesId()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        product.setCategory(category);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    public List<ProductResponse> getProductByCategoryId(String categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        return productRepository.findAllByCategory(category).stream().map(productMapper::toProductResponse).toList();
    }

    public String deleteProduct(String productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return productId + " deleted";
    }
}
