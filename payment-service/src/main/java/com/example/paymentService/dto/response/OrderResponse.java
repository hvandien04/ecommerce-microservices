package com.example.paymentService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponse {
    private String orderId;
    private String userId;
    private String status;
    private BigDecimal totalAmount;
    private Instant createdAt;
}
