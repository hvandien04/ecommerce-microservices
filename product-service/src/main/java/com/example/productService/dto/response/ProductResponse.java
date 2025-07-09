package com.example.productService.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String productsId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String categoryName;
    private List<ProductImageResponse> productImages;
    private List<ProductAttributeResponse> productAttributes;
}
