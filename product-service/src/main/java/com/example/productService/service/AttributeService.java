package com.example.productService.service;

import com.example.productService.dto.request.ProductAttributeRequest;
import com.example.productService.entity.Product;
import com.example.productService.entity.ProductAttribute;
import com.example.productService.exception.AppException;
import com.example.productService.exception.ErrorCode;
import com.example.productService.repository.ProductAttributeRepository;
import com.example.productService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttributeService {
    private final ProductAttributeRepository productAttributeRepository;
    private final IdGeneratorService idGeneratorService;
    private final ProductRepository productRepository;

    public String CreateAttribute(String ProductId, ProductAttributeRequest request){
        Product product = productRepository.findById(ProductId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        ProductAttribute productAttribute = ProductAttribute.builder()
               .productAttributesId(idGeneratorService.generateRandomId("ATT",productAttributeRepository::existsById))
               .key(request.getKey())
               .value(request.getValue())
               .products(product)
               .build();
        return productAttributeRepository.save(productAttribute).getProductAttributesId() + " created";
    }

    public String DeletedAttribute(String attributeId){
        productAttributeRepository.deleteById(attributeId);
        return attributeId + " deleted";
    }
}
