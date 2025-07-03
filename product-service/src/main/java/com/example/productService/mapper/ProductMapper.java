package com.example.productService.mapper;

import com.example.productService.dto.request.ProductRequest;
import com.example.productService.dto.response.ProductResponse;
import com.example.productService.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest request);
    ProductResponse toProductResponse(Product product);

    void updateProduct(@MappingTarget Product product, ProductRequest request);
}
