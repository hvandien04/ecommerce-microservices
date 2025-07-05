package com.example.productService.repository;

import com.example.productService.dto.response.ProductAttributeResponse;
import com.example.productService.entity.ProductAttribute;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductAttributeMapper {

    ProductAttribute toProductAttribute(ProductAttributeResponse response);


}
