package com.example.cartService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttributeResponse {
    private String productAttributesId;
    private String key;
    private String value;
}
