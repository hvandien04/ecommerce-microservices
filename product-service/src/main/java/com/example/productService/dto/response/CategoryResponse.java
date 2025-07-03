package com.example.productService.dto.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private String categoriesId;
    private String name;
    private String slug;
    private List<ProductResponse> products;
}
