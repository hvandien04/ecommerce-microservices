package com.example.productService.service;

import com.example.productService.dto.request.ProductImageRequest;
import com.example.productService.entity.Product;
import com.example.productService.entity.ProductImage;
import com.example.productService.exception.AppException;
import com.example.productService.exception.ErrorCode;
import com.example.productService.repository.ProductImageRepository;
import com.example.productService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final IdGeneratorService idGeneratorService;
    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

    public String createImage(String productId, ProductImageRequest request){
        Product product = productRepository.findById(productId).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        ProductImage productImage = ProductImage.builder()
               .id(idGeneratorService.generateRandomId("IMG",productImageRepository::existsById))
               .products(product)
               .imageUrl(request.getImageUrl())
               .build();
        return productImageRepository.save(productImage).getId() + " created";
    }

    public String deleteImage(String imageId){
        productImageRepository.deleteById(imageId);
        return imageId + " deleted";
    }
}
