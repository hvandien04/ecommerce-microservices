package com.example.cartService.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartResponse {
    private Long id;
    private String userId;
    private String productId;
    private Integer quantity;
}